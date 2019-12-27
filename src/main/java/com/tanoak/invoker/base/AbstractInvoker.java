package com.tanoak.invoker.base;

import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.utils.TaskQueue;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author tanoak
 * @date 2019/12/25
 */
public abstract class AbstractInvoker implements Invoker {
    /**
     * 表名
     */
    protected String tableName;
    /**
     * 类名
     */
    protected String className;
    /**
     * 列信息列表
     */
    protected List<ColumnInfo> columnInfoList;
    protected TaskQueue taskQueue = new TaskQueue();
    /**
     * 手动创建线程池
     */
    private ExecutorService executorPool = new ThreadPoolExecutor(6, 6,
            2L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private void initDataSource(List<ColumnInfo> columnInfoList) {

        getTableInfos(columnInfoList);
    }

    /**
     * 获取表信息
     *
     * @param columnInfoList 列信息列表
     */
    protected abstract void getTableInfos(List<ColumnInfo> columnInfoList);

    /**
     * 初始化任务
     */
    protected abstract void initTasks();

    @Override
    public void execute(List<ColumnInfo> columnInfoList) {
        try {
            initDataSource(columnInfoList);
            initTasks();
            while (!taskQueue.isEmpty()) {
                AbstractTask task = taskQueue.poll();
                executorPool.execute(task::run);
            }
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
