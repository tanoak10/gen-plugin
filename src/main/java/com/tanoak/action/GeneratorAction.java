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
import com.tanoak.exception.FileException;
import com.tanoak.invoker.SingleInvoker;
import com.tanoak.invoker.base.Invoker;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
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
        String moduleName = checkMoreModule(e);
//        初始化路径
        FileUtils.projectPath = e.getProject().getBasePath() + moduleName + File.separator + "src" + File.separator +
                "main" + File.separator + "java" + File.separator;
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
        String[] plist = StrUtil.split(psiJavaFile.getPackageName(), ".");

        ConfigUtil.initConfig("tanoak", getBasePackage(plist), plist[plist.length - 1], checkDao(plist));
        WriteCommandAction.runWriteCommandAction(e.getProject(),
                () -> single(StrUtil.removeAll(psiJavaFile.getName(), ".java"), columnInfoList));
        //显示对话框
        ApplicationManager.getApplication().saveAll();
        VirtualFileManager.getInstance().syncRefresh();
        Messages.showMessageDialog(e.getProject(), "success", "成功", null);

    }

    /**
     * 检查Dao
     *
     * @param plist plist
     * @return {@link Boolean} true 包含dao false 不包含
     */
    private Boolean checkDao(String[] plist) {
        for (String s : plist) {
            if (s.contains("dao")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取基础包名
     *
     * @param plist plist
     * @return {@link String}
     */
    private String getBasePackage(String[] plist) {
        StringBuilder res = new StringBuilder();
        for (String s : plist) {
            if (s.contains("dao") || s.equals(plist[plist.length - 1])) {
                break;
            }
            res.append(s).append(".");
        }
        return res.toString();
    }

    /**
     * 检查是否是多模块项目
     *
     * @param event event
     * @return {@link String} 模块名
     */
    private String checkMoreModule(AnActionEvent event) {
        String[] split = StrUtil.split(event.getDataContext().toString(), "/");
        //模块名
        String moduleName = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(event.getProject().getName())) {
                moduleName = split[i + 1];
                break;
            }
        }
        if ("src".equals(moduleName)) {
            return "";
        }
        return File.separator + moduleName;
    }

    /**
     * 生成文件入口
     *
     * @param className      类名
     * @param columnInfoList 列信息列表
     */
    private static void single(String className, List<ColumnInfo> columnInfoList) {
        try {
            Invoker invoker = new SingleInvoker.Builder()
                    .setTableName(StrUtil.toUnderlineCase(className))
                    .setClassName(className)
                    .build();
            invoker.execute(columnInfoList);
        } catch (FileException fe) {
            Messages.showErrorDialog("生成失败，有文件已存在", "警告");
        }
    }

}
