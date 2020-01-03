package com.tanoak.template;

/**
 * 类模板
 *
 * @author tanoak
 * @date 2019/12/27
 */
public class DaoTemplate extends BaseModel {

    private String daoPackageName;
    private String className;
    private String entityName;
    /**
     * 属性类型名称
     */
    private String propertyTypeName;

    public DaoTemplate() {
    }

    public StringBuilder build(DaoTemplate daoTemplate) {
        StringBuilder dao = new StringBuilder();
        dao.append("package ")
                .append(daoTemplate.getBasePackageName())
                .append(daoTemplate.getDaoPackageName())
                .append(";\n")
                .append("\n")
                .append("import ").append(daoTemplate.getBasePackageName())
                .append(daoTemplate.getEntityPackageName())
                .append(".")
                .append(daoTemplate.getClassName())
                .append(";\n")
                .append("import org.apache.ibatis.annotations.Mapper;\n")
                .append("\n").append("import java.util.List;\n")
                .append("\n").append("/**\n")
                .append(" * @author ").append(daoTemplate.getAuthor())
                .append("\n").append(" * @date  ").append(daoTemplate.getDate()).append("\n")
                .append(" */\n").append("@Mapper\n");
        dao.append("public interface ").append(daoTemplate.getClassName()).append("Dao {\n")
                .append("\n");
        //单个查询
        dao.append("    ").append(daoTemplate.getClassName()).append(" ")
                .append("selectByPrimaryKey(")
                .append(daoTemplate.getPropertyTypeName()).append(" id);\n")
                .append("\n");
        //列表查询
        dao.append("    List<").append(daoTemplate.getClassName())
                .append("> selectList();\n")
                .append("\n");
        //插入
        dao.append("    int insert(").append(daoTemplate.getClassName()).append(" ")
                .append(daoTemplate.getEntityName())
                .append(");\n").append("\n");
        //批量插入
        dao.append("    int insertBatch(List<")
                .append(daoTemplate.getClassName()).append("> ").append(daoTemplate.getEntityName()).append("s);\n").append("\n");
        //更新
        dao.append("    int updateByPrimaryKey(").append(daoTemplate.getPropertyTypeName())
                .append(" id);\n")
                .append("\n") ;
        //删除
        dao.append("    int deleteByPrimaryKey(").append(daoTemplate.getPropertyTypeName()).append(" id);\n")
                .append("\n")
                .append("\n")
                .append("}");
        return dao;
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
}
