package com.tanoak.test1;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnActionEvent event = null;
//        String str = "EditorComponent file=file:///Users/tanoak/work_space/java/tempProject/tanoak-boot/tanoak-boot-parent/tanoak-task/src/main/java/cn/tanoak/task/dao/entity/Hello.java";
//        String pName = "tanoak-boot-parent";
        String[] plist = {"com", "tanaok", "dao", "entity"};
        String[] plist2 = {"com", "tanaok", "entity"};
        System.out.println(getBasePackage(plist2));
        System.out.println("aaa");

    }

    private static String getBasePackage(String[] plist) {
        StringBuilder res = new StringBuilder();
        for (String s : plist) {
            if (s.contains("dao") || s.equals(plist[plist.length - 1])) {
                break;
            }
            res.append(s).append(".");
        }
        return res.toString();

//        for (int i = 0; i < plist.length; i++) {
//
//        }
//        List<String> list = new ArrayList<>();
//        CollUtil.addAll(list, plist);
//        list.remove(pglist.length - 1);
//        String pgStr = "";
//        for (String s : list) {
//            pgStr += s + ".";
//        }

//        return "";
    }

    private static String checkMoreModule(AnActionEvent event) {
        String str = "EditorComponent file=file:///Users/tanoak/work_space/java/tempProject/tanoak-boot/tanoak-boot-parent/tanoak-task/src/main/java/cn/tanoak/task/dao/entity/Hello.java";
        String pName = "tanoak-boot-parent";
        String[] split = StrUtil.split(str, "/");
        //模块名
        String moduleName = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(pName)) {
                moduleName = split[i + 1];
            }
        }
        if ("src".equals(moduleName)) {
            return "";
        }
        return File.separator + moduleName;
    }
}
