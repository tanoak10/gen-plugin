package com.tanoak.template;

/**
 * 基本模板
 *
 * @author tanoak
 * @date 2019/12/27
 */
public class BaseModel {

    /**
     * 基本包名称
     */
    protected String basePackageName;
    /**
     * 作者
     */
    protected String author;
    /**
     * 日期
     */
    protected String date;
    /**
     * entity层包名称
     */
    protected String entityPackageName;

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntityPackageName() {
        return entityPackageName;
    }

    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }
}
