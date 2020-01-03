package com.tanoak.template;

/**
 * 类模板
 *
 * @author tanoak
 * @date 2019/12/27
 */
public class ServiceTemplate extends BaseModel {

    private String servicePackageName;
    private String className;
    private String entityName;
    /**
     * 属性类型名称
     */
    private String propertyTypeName;

    public ServiceTemplate() {
    }

    public StringBuilder build(ServiceTemplate serviceTemplate) {
        StringBuilder service = new StringBuilder();
        service.append("package ")
                .append(serviceTemplate.getBasePackageName())
                .append(serviceTemplate.getServicePackageName())
                .append(";\n")
                .append("import ").append(serviceTemplate.getBasePackageName())
                .append(serviceTemplate.getEntityPackageName())
                .append(".")
                .append(serviceTemplate.getClassName())
                .append(";\nimport java.util.List;\n" + "\n" + "/**\n")
                .append(" * @author ")
                .append(serviceTemplate.getAuthor())
                .append("\n")
                .append(" * @date  ")
                .append(serviceTemplate.getDate())
                .append("\n */\n")
                .append("public interface ").append(serviceTemplate.getClassName()).append("Service {\n\n");
        //单个查询
        service.append("    ").append(serviceTemplate.getClassName()).append(" findById(")
                .append(serviceTemplate.getPropertyTypeName()).append(" id);\n\n");
        // 查询列表
        service.append("    List<").append(serviceTemplate.getClassName()).append("> findList();\n").append("\n");
        //保存
        service.append("    int save(").append(serviceTemplate.getClassName()).append(" ").
                append(serviceTemplate.getEntityName()).append(");\n").append("\n");
        //批量保存
        service.append("    int saveBatch(List<").append(serviceTemplate.getClassName()).append("> ")
                .append(serviceTemplate.getEntityName()).append("s);\n")
                .append("}");

        return service;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
    }
}
