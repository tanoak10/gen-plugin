package com.tanoak.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaFile;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.invoker.SingleInvoker;
import com.tanoak.invoker.base.Invoker;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 发电机的行动
 * 生成动作类
 *
 * @author tanoak
 * @date 2019/12/26
 */
public class GeneratorAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFileManager.getInstance().syncRefresh();
        ApplicationManager.getApplication().saveAll();
//        初始化路径
        String path = e.getProject().getBasePath() + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        FileUtils.projectPath = path;
//        //获取当前操作的类文件
        PsiJavaFile psiJavaFile = (PsiJavaFile) e.getData(CommonDataKeys.PSI_FILE);
        PsiField[] allFields = psiJavaFile.getClasses()[0].getAllFields();
        List<ColumnInfo> columnInfoList = new ArrayList<>();

        for (PsiField psiField : allFields) {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setType(psiField.getType().getCanonicalText());
            columnInfo.setColumnName(StrUtil.toUnderlineCase(psiField.getName()));
            columnInfo.setPropertyName(psiField.getName());
            if ("id".equals(psiField.getName())) {
                columnInfo.setPrimaryKey(true);
            }
            columnInfoList.add(columnInfo);
        }
        //初始化配置
        String[] pglist = StrUtil.split(psiJavaFile.getPackageName(), ".");
        List<String> list = new ArrayList<>();
        CollUtil.addAll(list, pglist);
        list.remove(pglist.length - 1);
        String pgStr = "";
        for (String s : list) {
            pgStr += s + ".";
        }
        ConfigUtil.initConfig("tanoak", pgStr, pglist[pglist.length - 1]);
        WriteCommandAction.runWriteCommandAction(e.getProject(),
                () -> single(StrUtil.removeAll(psiJavaFile.getName(), ".java"), columnInfoList));
        //显示对话框
        ApplicationManager.getApplication().saveAll();
        VirtualFileManager.getInstance().syncRefresh();
        Messages.showMessageDialog(e.getProject(), "成功", "成功", null);
    }

    private static void single(String className, List<ColumnInfo> columnInfoList) {

        Invoker invoker = new SingleInvoker.Builder()
                .setTableName(StrUtil.toUnderlineCase(className))
                .setClassName(className)
                .build();
        invoker.execute(columnInfoList);
    }

}
