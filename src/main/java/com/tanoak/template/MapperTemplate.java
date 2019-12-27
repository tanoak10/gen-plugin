package com.tanoak.template;

/**
 * 类模板
 *
 * @author tanoak
 * @date 2019/12/27
 */
public class MapperTemplate extends BaseModel {

    private String daoPackageName;
    private String className;
    private String entityName;
    private String resultMap;
    private String columnMap;
    private String tableName;
    /**
     * 属性类型名称
     */
    private String propertyTypeName;

    public StringBuilder build(MapperTemplate mapperTemplate) {
        StringBuilder mapper = new StringBuilder();
        mapper.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        mapper.append("<mapper namespace=\"")
                .append(mapperTemplate.getBasePackageName())
                .append(mapperTemplate.getDaoPackageName())
                .append(".")
                .append(mapperTemplate.getClassName())
                .append("Dao\">\n");
        mapper.append("<resultMap id=\"")
                .append(mapperTemplate.getEntityName()).append("ResultMap\"")
                .append(" type=\"")
                .append(mapperTemplate.getBasePackageName())
                .append(mapperTemplate.getDaoPackageName())
                .append(".")
                .append(mapperTemplate.getClassName()).append("\">\n")
                .append(mapperTemplate.getResultMap())
                .append("</resultMap>");
        mapper.append(" <sql id=\"").append(mapperTemplate.getEntityName()).append("Columns\">\n")
                .append("        \n").append(mapperTemplate.getColumnMap())
                .append("</sql>");
        //查询
        mapper.append("    <select id=\"selectByPrimaryKey\" resultMap=\"")
                .append(mapperTemplate.getEntityName()).append("ResultMap\">\n")
                .append("        SELECT\n").append("        <include refid=\"")
                .append(mapperTemplate.getEntityName()).append("Columns\" />\n")
                .append("        FROM ").append(mapperTemplate.getTableName()).append("\n")
                .append("        <where>\n").append("        ").append(mapperTemplate.getTableName())
                .append(".id = ${Id}\n").append("        </where>\n").append("    </select>");
        return mapper;
    }

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getResultMap() {
        return resultMap;
    }

    public void setResultMap(String resultMap) {
        this.resultMap = resultMap;
    }

    public String getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(String columnMap) {
        this.columnMap = columnMap;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}