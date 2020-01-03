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
    private String insertValues;
    private String updateProperties;
    /**
     * 属性类型名称
     */
    private String propertyTypeName;
    private String insertProperties;
    private String insertBatchProperties;

    public StringBuilder build(MapperTemplate mapperTemplate) {
        StringBuilder mapper = new StringBuilder();
        mapper.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        mapper.append("\n<mapper namespace=\"")
                .append(mapperTemplate.getBasePackageName())
                .append(mapperTemplate.getDaoPackageName())
                .append(".")
                .append(mapperTemplate.getClassName())
                .append("Dao\">\n");
        mapper.append("<resultMap id=\"")
                .append(mapperTemplate.getEntityName()).append("ResultMap\"")
                .append(" type=\"")
                .append(mapperTemplate.getBasePackageName())
                .append(mapperTemplate.getEntityPackageName())
                .append(".")
                .append(mapperTemplate.getClassName()).append("\">\n        ")
                .append(mapperTemplate.getResultMap()).append("\n")
                .append("</resultMap>\n\n");
        mapper.append(" <sql id=\"").append(mapperTemplate.getEntityName()).append("Columns\">\n")
                .append("        \n        ").append(mapperTemplate.getColumnMap())
                .append("\n </sql>\n");
        //查询
        mapper.append(" <select id=\"selectByPrimaryKey\" resultMap=\"")
                .append(mapperTemplate.getEntityName()).append("ResultMap\">\n")
                .append("        SELECT\n").append("        <include refid=\"")
                .append(mapperTemplate.getEntityName()).append("Columns\" />\n")
                .append("        FROM ").append(mapperTemplate.getTableName()).append("\n")
                .append("        <where>\n").append("        ").append(mapperTemplate.getTableName())
                .append(".id = #{id}\n").append("        </where>\n").append(" </select>\n");
        mapper.append(" <select id=\"selectList\" resultMap=\"").append(mapperTemplate.getEntityName())
                .append("ResultMap\">\n")
                .append("        SELECT\n").append(
                "        <include refid=\"").append(mapperTemplate.getEntityName()).append("Columns\" />\n")
                .append("        FROM ")
                .append(mapperTemplate.getTableName()).append("\n")
                .append(" </select>\n");
        mapper.append(" <insert id=\"insert\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n")
                .append("        INSERT INTO ").append(mapperTemplate.getTableName()).append("(\n            ")
                .append(mapperTemplate.getInsertProperties()).append("\n        )\n        VALUES (\n            ")
                .append(mapperTemplate.getInsertValues()).append("\n        )\n")
                .append("    </insert>\n");
        mapper.append(" <insert id=\"insertBatch\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n")
                .append("        INSERT INTO ").append(mapperTemplate.getTableName()).append("(\n            ")
                .append(mapperTemplate.getInsertProperties()).append("\n        )\n        VALUES (\n")
                .append("        <foreach collection =\"list\" item=\"").append(mapperTemplate.getEntityName())
                .append("\" separator =\",\">").append("\n            (")
                .append(mapperTemplate.getInsertBatchProperties()).append("\n        )\n")
                .append("        )\n        </foreach>\n")
                .append(" </insert>\n");
        //更新
        mapper.append(" <update id=\"updateByPrimaryKey\">\n        UPDATE ").append(mapperTemplate.getTableName())
                .append(" SET\n")
                .append("        ").append(mapperTemplate.getUpdateProperties()).append("\n")
                .append("        WHERE id = #{id}\n </update>\n")
                .append("</mapper>");
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

    public String getInsertProperties() {
        return insertProperties;
    }

    public void setInsertProperties(String insertProperties) {
        this.insertProperties = insertProperties;
    }

    public String getInsertValues() {
        return insertValues;
    }

    public void setInsertValues(String insertValues) {
        this.insertValues = insertValues;
    }

    public String getUpdateProperties() {
        return updateProperties;
    }

    public void setUpdateProperties(String updateProperties) {
        this.updateProperties = updateProperties;
    }

    public void setInsertBatchProperties(String insertBatchProperties) {
        this.insertBatchProperties = insertBatchProperties;
    }

    public String getInsertBatchProperties() {
        return insertBatchProperties;
    }
}