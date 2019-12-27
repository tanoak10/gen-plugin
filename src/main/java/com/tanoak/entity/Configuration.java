package com.tanoak.entity;


import java.io.Serializable;

/**
 * @author 三木
 * @date 2019/12/23
 */
public class Configuration implements Serializable {
    private String author;
    private String packageName;
    private Path path;
    private String tableName ;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
