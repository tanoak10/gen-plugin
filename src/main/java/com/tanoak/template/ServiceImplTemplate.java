package com.tanoak.template;

/**
 * 类模板
 *
 * @author tanoak
 * @date 2019/12/27
 */
public class ServiceImplTemplate extends BaseModel {

    private String servicePackageName;
    private String className;
    private String entityName;
    /**
     * 属性类型名称
     */
    private String propertyTypeName;
    private String daoPackageName;
    private String impl;
    private String serviceImport;

    public ServiceImplTemplate() {
    }

    public StringBuilder build(ServiceImplTemplate serviceImplTemplate) {
        StringBuilder service = new StringBuilder();
        service.append("package ")
                .append(serviceImplTemplate.getBasePackageName())
                .append(serviceImplTemplate.getServicePackageName())
                .append(";\n")
                .append("import ").append(serviceImplTemplate.getBasePackageName())
                .append(serviceImplTemplate.getEntityPackageName()).append(".")
                .append(serviceImplTemplate.getClassName()).append(";\n")
                .append("import org.springframework.stereotype.Service;\n")
                .append("import javax.annotation.Resource;\n")
                .append("import ")
                .append(serviceImplTemplate.getBasePackageName())
                .append(serviceImplTemplate.getDaoPackageName()).append(".")
                .append(serviceImplTemplate.getClassName()).append("Dao")
                .append(";\n")
                .append(";\nimport java.util.List;\n" + "\n" + "/**\n")
                .append(" * @author ")
                .append(serviceImplTemplate.getAuthor())
                .append("\n")
                .append(" * @date  ")
                .append(serviceImplTemplate.getDate())
                .append("\n */\n")
                .append("@Service\n")
                .append("public class ").append(serviceImplTemplate.getClassName()).append("Service").append(serviceImplTemplate.getImpl()).append("{\n\n");
        service.append("    @Resource\n").append("    ")
                .append("private ").append(serviceImplTemplate.getClassName()).append("Dao ")
                .append(serviceImplTemplate.getEntityName()).append("Dao;\n");
        //单个查询
        service.append("    @Override\n")
                .append("    public ").append(serviceImplTemplate.getClassName()).append(" findById(")
                .append(serviceImplTemplate.getPropertyTypeName()).append(" id){\n")
                .append("return ")
                .append(serviceImplTemplate.getEntityName()).append("Dao.")
                .append("selectByPrimaryKey(id);\n")
                .append("\n    }\n");
        // 查询列表
        service.append("    @Override\n")
                .append("    public List<").append(serviceImplTemplate.getClassName()).append("> findList(){")
                .append("return ")
                .append(serviceImplTemplate.getEntityName()).append("Dao.")
                .append("selectList();\n")
                .append("\n    }\n");
        //保存
        service.append("    @Override\n")
                .append("    public int save(").append(serviceImplTemplate.getClassName()).append(" ").
                append(serviceImplTemplate.getEntityName()).append("){")
                .append("return ")
                .append(serviceImplTemplate.getEntityName()).append("Dao.")
                .append("insert(").append(serviceImplTemplate.getEntityName()).append(");")
                .append("\n    }\n");
        //批量保存
        service.append("    @Override\n")
                .append("    public int saveBatch(List<").append(serviceImplTemplate.getClassName()).append("> ")
                .append(serviceImplTemplate.getEntityName()).append("s){")
                .append("return ")
                .append(serviceImplTemplate.getEntityName()).append("Dao.")
                .append("insertBatch(").append(serviceImplTemplate.getEntityName()).append("s);")
                .append("\n    }\n}\n");

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

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getImpl() {
        return impl;
    }

    public void setImpl(String impl) {
        this.impl = impl;
    }

    public String getServiceImport() {
        return serviceImport;
    }

    public void setServiceImport(String serviceImport) {
        this.serviceImport = serviceImport;
    }
}
