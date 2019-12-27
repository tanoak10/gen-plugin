package com.tanoak.task.base;

import com.tanoak.entity.ColumnInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 抽象的任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public abstract class AbstractTask implements Serializable {
    /**
     * 表名
     */
    protected String tableName;
    /**
     * 类名
     */
    protected String className;

    /**
     * 表信息
     */
    protected List<ColumnInfo> columnInfoList;


    /**
     * 抽象的任务
     * Controller、Service、Dao
     *
     * @param className 类名
     */
    public AbstractTask(String className) {
        this.className = className;
    }

    /**
     * 抽象的任务
     * Entity
     *
     * @param className      类名
     * @param columnInfoList 表信息
     */
    public AbstractTask(String className, List<ColumnInfo> columnInfoList) {
        this.className = className;
        this.columnInfoList = columnInfoList;
    }


    /**
     * 抽象的任务
     * Mapper
     *
     * @param tableName      表名
     * @param className      类名
     * @param columnInfoList 表信息
     */
    public AbstractTask(String tableName, String className, List<ColumnInfo> columnInfoList) {
        this.tableName = tableName;
        this.className = className;
        this.columnInfoList = columnInfoList;
    }

    /**
     * 运行
     */
    public abstract void run();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }
}
