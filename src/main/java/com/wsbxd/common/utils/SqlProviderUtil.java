package com.wsbxd.common.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: Sql Provider 工具
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2019/1/16 10:02
 */
public class SqlProviderUtil {

    private final static String ID = "id";

    /**
     * 根绝id批量修改拼接
     * @param list      集合
     * @param <T>       <T>
     * @return 批量修改拼接sql
     */
    public static <T> String updateListJoint(List<T> list){
        // 获取字段名称
        List<String> fieldNameList = getFieldNameList(list.get(0));
        Map<T,Object> entityAndIdMap = getEntityAndId(list);
        Map<String,StringBuilder> fieldAndSql = new HashMap<String,StringBuilder>(16){
            {
                list.forEach(entity -> fieldNameList.forEach(field -> {
                    if (!ID.equals(field)){
                        Object value = parseFieldGetValue(entity,field);
                        if (value != null){
                            Object id = entityAndIdMap.get(entity);
                            if (ReflectUtil.hasCharacter(id)){
                                id = "'" + id + "'";
                            }
                            if (this.containsKey(field)){
                                this.get(field).append(" WHEN ").append(id).append(" THEN ").append(value).append(" ");
                            }else{
                                this.put(field,new StringBuilder(StrKit.toUnderlineCase(field) + " = CASE id WHEN " + id + " THEN " + value));
                            }
                        }
                    }
                }));
            }
        };
        StringBuilder sqlAll = new StringBuilder();
        fieldAndSql.forEach((field,sql) -> sqlAll.append(sqlAll.length() == 0 ? " SET " : ", ").append(sql).append(" END "));
        return sqlAll.toString();
    }

    /**
     * in拼接sql
     * @param fieldName     字段名称
     * @param values        值集合
     * @param isNum         是否是数字
     * @return  sql
     */
    public static String inJoint(String fieldName, List<?> values, boolean isNum){
        StringBuilder sql = new StringBuilder();
        if(StringUtils.isNotEmpty(fieldName) && !values.isEmpty()){
            StringBuilder value = new StringBuilder();
            values.forEach(o -> value.append(values.indexOf(o) == 0 ? (isNum?o.toString():"'"+o+"'") : (isNum?","+o:",'"+o+"'")));
            if(StringUtils.isNotBlank(value)){
                sql.append(" and ").append(fieldName).append(" in (").append(value.toString()).append(")");
            }
        }
        return sql.delete(0,4).toString();
    }

    public static String propertyAndValueInsertJoint(Object object){
        try {
            StringBuilder propertys = new StringBuilder("(");
            StringBuilder values = new StringBuilder(" values(");
            Class<?> aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field:fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null){
                    String name = StrKit.toUnderlineCase(field.getName());
                    propertys.append("`").append(name).append("`").append(",");
                    fieldValueInJoint(values, field, value);
                }
            }
            int propertysLength = propertys.length();
            propertys.delete((propertysLength-1),propertysLength).append(")");
            int valuesLength = values.length();
            values.delete((valuesLength-1),valuesLength).append(")");
            return propertys.append(values).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String propertyAndValueUpdateJoint(Object object) {
        try {
            StringBuilder sql = new StringBuilder();
            Class<?> aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field:fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null){
                    String name = StrKit.toUnderlineCase(field.getName());
                    sql.append("`").append(name).append("`").append(" = ");
                    fieldValueInJoint(sql, field, value);
                }
            }
            int length = sql.length();
            return sql.delete((length-1),length).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String propertyAndValueWhereJoint(Object object) {
        try {
            StringBuilder sql = new StringBuilder(" where ");
            Class<?> aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field:fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null){
                    String name = StrKit.toUnderlineCase(field.getName());
                    Class<?> type = field.getType();
                    sql.append(name).append(" = ");
                    if (type == String.class){
                        sql.append("'").append(value).append("' and ");
                    }else if (type == Date.class){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = format.format(value);
                        sql.append("'").append(date).append("' and ");
                    }else {
                        sql.append(value).append(" and ");
                    }
                }
            }
            int length = sql.length();
            return sql.delete((length-4),length).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字段与值sql拼接
     * @param sql       sql
     * @param field     字段
     * @param value     字段值
     */
    private static void fieldValueInJoint(StringBuilder sql, Field field, Object value) {
        Class<?> type = field.getType();
        if (type == String.class){
            sql.append("'").append(value).append("',");
        }else if (type == Date.class){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(value);
            sql.append("'").append(date).append("',");
        }else {
            sql.append(value).append(",");
        }
    }

    /**
     * 获取对象中字段名称集合,排除id
     * @param t     对象
     * @param <T>   <T>
     * @return  字段名称集合
     */
    private static <T> List<String> getFieldNameList(T t) {
        return Arrays.stream(t.getClass().getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
    }

    /**
     * 获取Map<T, id>
     * @param list      集合
     * @param <T>       <T>
     * @return  Map<T, id>
     */
    private static <T> Map<T, Object> getEntityAndId(List<T> list) {
        return new HashMap<T, Object>(16){{
            list.forEach(e -> this.put(e, ReflectUtil.getValue(e,"id"))); }
        };
    }

    /**
     * 获取id集合
     * @param list  集合
     * @param <T>   <T>
     * @return  id集合
     */
    public static <T> List<Object> getIdList(List<T> list){
        return list.stream().map(entity -> ReflectUtil.getValue(entity,"id")).collect(Collectors.toList());
    }

    /**
     * 解析字段并获取值
     * @param target        对象
     * @param fieldName     字段名
     * @return  值
     */
    private static Object parseFieldGetValue(Object target, String fieldName) {
        Class<?> aClass = target.getClass();
        try {
            Field field = aClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(target);
            if (ObjectUtils.allNotNull(value)){
                Class<?> type = field.getType();
                if (type == Date.class){
                    return "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value) + "'";
                }else if (type == String.class || type == char.class || type == char[].class){
                    return "'" + value.toString() + "'";
                }else{
                    return value.toString();
                }
            }
            return null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(aClass.getName() + " 解析 " + fieldName + " 字段获取值错误!");
        }
    }

}
