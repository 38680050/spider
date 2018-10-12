package com.wsbxd.common.utils;


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
     * 根据属性，获取get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethod(Object ob , String name)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(ob);
            }
        }
        return null;
    }


    /**
     * 根据属性，拿到set方法，并把值set到对象中
     * @param obj 对象
     * @param clazz 对象的class
     * @param filedName 需要设置值得属性
     * @param typeClass 需要设置值得字段类型
     * @param value 需要设置的值
     */
    public static void setValue(Object obj,Class<?> clazz,String filedName,Class<?> typeClass,Object value){
        filedName = removeLine(filedName);
        String methodName = "set" + filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        try{
            Method method =  clazz.getDeclaredMethod(methodName, new Class[]{typeClass});
            method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 根据属性，拿到set方法，并把值set到对象中
     * @param obj 对象
     * @param filedName 需要设置值得属性
     * @param value 需要设置的值
     */
    public static void setValue1(Object obj,String filedName,Object value) throws Exception{
        filedName = removeLine(filedName);
        String methodName = "set" + filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        Class<?> typeClass = obj.getClass().getDeclaredField(filedName).getType();
        try{
            Method method =  obj.getClass().getDeclaredMethod(methodName, new Class[]{typeClass});
            method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
        }catch(Exception ex){
            ex.printStackTrace();
        }
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
            String newStr2 = newStr.replace(UNDERLINE, "");
            return newStr2;
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
