package com.tanoak.invoker.base;

import com.tanoak.entity.ColumnInfo;

import java.util.List;

/**
 * 调用程序
 *
 * @author tanoak
 * @date 2019/12/24
 */
public interface Invoker {

    /**
     * 执行
     *
     * @param columnInfoList 列信息列表
     */
    void execute(List<ColumnInfo> columnInfoList);

}
