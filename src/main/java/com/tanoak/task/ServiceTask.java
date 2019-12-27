package com.tanoak.task;

import cn.hutool.core.date.DateUtil;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.template.ServiceTemplate;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;
import com.tanoak.utils.StringUtil;

import java.util.List;

/**
 * 接口任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class ServiceTask extends AbstractTask {

    public ServiceTask(String className, List<ColumnInfo> tableInfos) {
        super(className,tableInfos);
    }

    @Override
    public void run() {
        // 生成Service接口填充数据
        System.out.println("Generating " + className + "Service.java");
        ColumnInfo columnInfo = columnInfoList.stream().filter(info -> info.getPropertyName().equalsIgnoreCase("id")).findFirst().get();
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getService());
        String fileName = className + "Service.java";
        ServiceTemplate serviceTemplate = new ServiceTemplate() ;
        serviceTemplate.setBasePackageName(ConfigUtil.getConfiguration().getPackageName());
        serviceTemplate.setAuthor(ConfigUtil.getConfiguration().getAuthor());
        serviceTemplate.setDate(DateUtil.now());
        serviceTemplate.setEntityPackageName(ConfigUtil.getConfiguration().getPath().getEntity());

        serviceTemplate.setServicePackageName(ConfigUtil.getConfiguration().getPath().getService());
        serviceTemplate.setClassName(className);
        serviceTemplate.setEntityName(StringUtil.firstToLowerCase(className));
        serviceTemplate.setPropertyTypeName(columnInfo.getType());

        // 生成Service接口文件
        FileUtils.generateToJava(serviceTemplate.build(serviceTemplate), filePath + fileName);
    }
}
