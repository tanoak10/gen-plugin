package com.tanoak.utils;

import cn.hutool.core.io.FileUtil;
import com.tanoak.exception.FileException;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 文件工具
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class FileUtils {

    /**
     * 生成Java
     *
     * @param data     填充数据
     * @param filePath 输出文件
     */
    public static void generateToJava(StringBuilder data, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            throw new FileException("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1) + " 已存在，请手动修改");
        }
        if (!FileUtil.exist(file.getParent())) {
            FileUtil.mkdir(file.getParent());
        }
        FileUtil.writeString(data.toString(), file, StandardCharsets.UTF_8);
    }

//    private static String getBasicProjectPath() {
//        String path = new File(Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(""))
//                .getFile()).getPath() + File.separator;
//        return path.substring(0, path.indexOf("target")) + "src" + File.separator + "main" + File.separator;
//    }

    /**
     * 获取源码路径
     *
     * @return {@link String}
     */
    public static String getSourcePath() {
        return projectPath;
    }

    public static String projectPath = "";

}
