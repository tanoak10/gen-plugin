package com.tanoak.task;

import cn.hutool.core.date.DateUtil;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.template.ServiceImplTemplate;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;
import com.tanoak.utils.StringUtil;

import java.util.List;

/**
 * 服务任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class ServiceImplTask extends AbstractTask {

    public ServiceImplTask(String className, List<ColumnInfo> tableInfos) {
        super(className, tableInfos);
    }

    @Override
    public void run() {
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getServiceImpl());
        String fileName = className + "ServiceImpl.java";
        System.out.println("Generating " + className + "ServiceImpl.java");
        ColumnInfo columnInfo = columnInfoList.stream().filter(info -> info.getPropertyName().equalsIgnoreCase("id")).findFirst().get();
        // 生成Service文件
        ServiceImplTemplate serviceImplTemplate = new ServiceImplTemplate();
        serviceImplTemplate.setBasePackageName(ConfigUtil.getConfiguration().getPackageName());
        serviceImplTemplate.setAuthor(ConfigUtil.getConfiguration().getAuthor());
        serviceImplTemplate.setDate(DateUtil.now());
        serviceImplTemplate.setEntityPackageName(ConfigUtil.getConfiguration().getPath().getEntity());
        serviceImplTemplate.setDaoPackageName(ConfigUtil.getConfiguration().getPath().getDao());

        serviceImplTemplate.setServicePackageName(ConfigUtil.getConfiguration().getPath().getServiceImpl());
        serviceImplTemplate.setClassName(className);
        serviceImplTemplate.setEntityName(StringUtil.firstToLowerCase(className));
        serviceImplTemplate.setPropertyTypeName(columnInfo.getType());
        serviceImplTemplate.setImpl("Impl implements " + className + "Service");
        serviceImplTemplate.setServiceImport("import " + ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getService() + "." + className + "Service;");
        FileUtils.generateToJava(serviceImplTemplate.build(serviceImplTemplate), filePath + fileName);
    }
}
