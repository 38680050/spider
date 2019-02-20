package com.wsbxd.common.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * description: 反射工具类
 *
 * @author chenhaoxuan
 * @date 2018/9/15 16:09
 */
public class ReflectUtil {

    private final static String UNDERLINE = "_";

    /**
     * 使用反射设置变量值
     *
     * @param target 被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可是常量！
     * @param value 值
     * @param <T> value类型，泛型
     */
    public static <T> void setValue(Object target,String fieldName,T value) {
        try {
            Class c = target.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用反射设置变量值
     *
     * @param target 被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可是常量！
     * @param value 值
     * @param <T> value类型，泛型
     */
    public static <T> void setValue(Object target, Class<?> clazz, String fieldName, T value) {
        try {
            Field f = clazz.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用反射获取变量值
     *
     * @param target 被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可以是常量
     * @param <T> 返回类型，泛型
     * @return 值
     */
    public static <T> T getValue(Object target,String fieldName) {
        T value = null;
        try {
            Class c = target.getClass();
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            value = (T) f.get(target);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 使用反射获取变量值
     *
     * @param target 被调用对象
     * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可以是常量
     * @param <T> 返回类型，泛型
     * @return 值
     */
    public static <T> T getValue(Object target, Class<?> clazz, String fieldName) {
        T value = null;
        try {
            Field f = clazz.getDeclaredField(fieldName);
            f.setAccessible(true);
            value = (T) f.get(target);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * * 处理字符串  如：  abc_dex ---> abcDex
     * * @param str
     * * @return
     * */
    private static  String removeLine(String str){
        if(null != str && str.contains(UNDERLINE)){
            int i = str.indexOf(UNDERLINE);
            char ch = str.charAt(i+1);
            char newCh = (ch+"").substring(0, 1).toUpperCase().toCharArray()[0];
            String newStr = str.replace(str.charAt(i+1), newCh);
            return newStr.replace(UNDERLINE, "");
        }
        return str;
    }

    /**
     * * 通过class类型获取获取对应类型的值
     * * @param typeClass class类型
     * * @param value 值
     * * @return Object
     * */
    private static Object getClassTypeValue(Class<?> typeClass, Object value){
        if(typeClass == int.class  || typeClass == Integer.class){
            if(null == value){
                return 0;
            }
            return Integer.parseInt(String.valueOf(value));
        }else if(typeClass == short.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == byte.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == double.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == long.class){
            if(null == value){
                return 0;
            }
            return value;
        }else if(typeClass == String.class){
            if(null == value){
                return "";
            }
            return value;
        }else if(typeClass == boolean.class){
            if(null == value){
                return true;
            }
            return value;
        }else if(typeClass == BigDecimal.class){
            if(null == value){
                return new BigDecimal(0);
            }
            return new BigDecimal(value+"");
        }else {
            return typeClass.cast(value);
        }
    }

}
