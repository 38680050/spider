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
     * 是否是Java字符类型
     * @param target    对象
     * @return  true/false
     */
    public static boolean hasCharacter(Object target){
        return target.getClass() == String.class || target.getClass() == char.class || target.getClass() == char[].class;
    }

}
