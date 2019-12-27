package com.tanoak.task;

import cn.hutool.core.date.DateUtil;
import com.tanoak.entity.ColumnInfo;
import com.tanoak.task.base.AbstractTask;
import com.tanoak.template.DaoTemplate;
import com.tanoak.utils.ConfigUtil;
import com.tanoak.utils.FileUtils;
import com.tanoak.utils.StringUtil;

import java.util.List;

/**
 * dao任务
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class DaoTask extends AbstractTask {

    public DaoTask(String className, List<ColumnInfo> infos) {
        super(className, infos);
    }

    @Override
    public void run() {
        // 生成Dao填充数据
        System.out.println("Generating " + className + "Dao.java");
        DaoTemplate daoTemplate = new DaoTemplate();
        daoTemplate.setBasePackageName(ConfigUtil.getConfiguration().getPackageName());
        daoTemplate.setEntityPackageName(ConfigUtil.getConfiguration().getPath().getEntity());
        daoTemplate.setAuthor(ConfigUtil.getConfiguration().getAuthor());
        daoTemplate.setDate(DateUtil.date().toDateStr());
        daoTemplate.setDaoPackageName(ConfigUtil.getConfiguration().getPath().getDao());
        daoTemplate.setClassName(className);
        ColumnInfo columnInfo = columnInfoList.stream().filter(info -> info.getPropertyName().equalsIgnoreCase("id")).findFirst().get();
        daoTemplate.setPropertyTypeName(columnInfo.getType());
        daoTemplate.setEntityName(StringUtil.firstToLowerCase(className));
        String filePath = FileUtils.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDao());
        String fileName = className + "Dao.java";
        // 生成dao文件
        FileUtils.generateToJava(daoTemplate.build(daoTemplate), filePath + fileName);
    }
}
