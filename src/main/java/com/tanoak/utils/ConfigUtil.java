package com.tanoak.utils;

import com.tanoak.entity.Configuration;
import com.tanoak.entity.Path;

/**
 * @author 三木
 * @date 2019/12/23
 * 配置类工具
 */
public class ConfigUtil {
    private static Configuration configuration = new Configuration();
    private ConfigUtil(){

    }
    public static void initConfig(String author, String packageName,String entity) {
        Path path = new Path();
        configuration.setAuthor(author);
        configuration.setPackageName(packageName);
        configuration.setPath(path);
        path.setController("controller");
        path.setService("service");
        path.setServiceImpl("service.impl");
        path.setDao("dao.mapper");
        path.setMapper("mapper");
        path.setEntity(entity);
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

}
