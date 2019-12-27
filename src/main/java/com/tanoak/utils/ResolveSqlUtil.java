package com.tanoak.utils;

//import cn.tanoak.test.JdbcType2JavaType;
//import cn.tanoak.test.POJOfield;
//import cn.tanoak.test.PoJoMaker;
//import cn.tanoak.test.TemplateUtil;

import cn.hutool.core.util.StrUtil;
import com.tanoak.entity.ColumnInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析Sql util
 *
 * @author tanoak
 * @date 2019/12/25
 */
public class ResolveSqlUtil {



    /**
     * 处理领域
     * 处理pojo的所有字段
     *
     * @param sql sql
     * @return Ppjo的实体类
     */
    public static List<ColumnInfo> handleField(String sql) {
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        String[] sqlArray = sql.split("\n");
        for (int i = 1; i < sqlArray.length - 1; i++) {

            String rawField = sqlArray[i].trim();
            String[] fieldArr = rawField.split(" ");
            if (rawField.contains("utf8") || rawField.contains("UTF8")) {
                continue;
            }
            if (rawField.contains(" KEY ")) {
                continue;
            }
            if (!fieldArr[0].contains("`")) {
                return null;
            }
            String field = fieldArr[0].replace("`", "");
            //如果是以下划线方式命名则需要转成小驼峰
            ColumnInfo columnInfo = new ColumnInfo();
            //是否主键
            if ("id".equalsIgnoreCase(field)) {
                columnInfo.setPrimaryKey(true);
            }
            //字段名
            columnInfo.setColumnName(field);
            String property = StrUtil.toCamelCase(field);
            //属性名
            columnInfo.setPropertyName(property);
            //将数据库类型转换为java类型
            String type = "";
            type = JdbcType2JavaType.map.get(fieldArr[1].toUpperCase().replaceAll("[^a-z^A-Z]", ""));
            if (type == null) {
                type = "Object";
            }
            columnInfo.setType(type);
            for (int j = 2; j < fieldArr.length; j++) {
                if (fieldArr[j].equalsIgnoreCase("COMMENT")) {
                    String comment = fieldArr[j + 1];
                    comment = comment.replace("'", "");
                    comment = comment.replace(",", "");
                    columnInfo.setComment(comment);
                    continue;
                }
            }
            if (columnInfo != null) {
                columnInfoList.add(columnInfo);
            }
        }

        return columnInfoList;
    }


    /**
     * 处理表名称的注释
     */
    private static String handleTableComment(String rawTableComment) {
        rawTableComment = rawTableComment.trim();
        String[] tableCommentArr = rawTableComment.split(" ");
        if (tableCommentArr.length == 0) {
            return "";
        }

        String tableComment = tableCommentArr[tableCommentArr.length - 1];
        if (!tableComment.contains("COMMENT")) {
            return "";
        }

        tableComment = tableComment.replace("COMMENT=", "");
        tableComment = tableComment.replace("'", "");
        tableComment = tableComment.replace(";", "");
        return tableComment;
    }
}