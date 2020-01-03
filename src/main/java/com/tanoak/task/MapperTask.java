package com.tanoak.task;

import cn.hutool.core.date.DateUtil;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.template.MapperTemplate;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;
import com.tanoak.utils.GeneratorUtil;
import com.tanoak.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class MapperTask extends AbstractTask {

    /**
     * 单表Mapper
     */
    public MapperTask(String className, String tableName, List<ColumnInfo> infos) {
        super(tableName, className, infos);
    }

    @Override
    public void run() {
        // 生成Mapper填充数据
        System.out.println("Generating " + className + "Mapper.xml");
        ColumnInfo columnInfo = columnInfoList.stream().filter(info -> info.getPropertyName().equalsIgnoreCase("id")).findFirst().get();
        MapperTemplate mapperTemplate = new MapperTemplate();
        mapperTemplate.setDaoPackageName(ConfigUtil.getConfiguration().getPath().getDao());
        mapperTemplate.setClassName(className);
        mapperTemplate.setPropertyTypeName(columnInfo.getType());
        mapperTemplate.setEntityName(StringUtil.firstToLowerCase(className));
        mapperTemplate.setResultMap(GeneratorUtil.generateMapperResultMap(columnInfoList));
        mapperTemplate.setColumnMap(GeneratorUtil.generateMapperColumnMap(tableName, columnInfoList));
        mapperTemplate.setTableName(tableName);
        mapperTemplate.setInsertProperties(GeneratorUtil.generateMapperInsertProperties(columnInfoList));
        mapperTemplate.setInsertBatchProperties(GeneratorUtil.generateMapperInsertBatchValues(columnInfoList, StringUtil.firstToLowerCase(className)));
        mapperTemplate.setInsertValues(GeneratorUtil.generateMapperInsertValues(columnInfoList));
        mapperTemplate.setUpdateProperties(GeneratorUtil.generateMapperUpdateProperties(columnInfoList));
        mapperTemplate.setBasePackageName(ConfigUtil.getConfiguration().getPackageName());
        mapperTemplate.setDate(DateUtil.now());
        mapperTemplate.setEntityPackageName(ConfigUtil.getConfiguration().getPath().getEntity());
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDao());
        String fileName = className + "Mapper.xml";
        // 生成Mapper文件
        FileUtils.generateToJava(mapperTemplate.build(mapperTemplate), filePath + fileName);
    }
}
