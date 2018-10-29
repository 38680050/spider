package com.wsbxd.common.utils;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * description: 动态bean
 *
 * @author chenhaoxuan
 * @version 1.0
 * @date 2018/10/29 10:38
 */
public class DynamicBean {

    /**
     * 动态生成的类
     */
    private Object object;
    /**
     * 存放属性名称以及属性的类型
     */
    private BeanMap beanMap;

    public DynamicBean(Map<String,Class> propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     * @param property 属性名
     * @param value 值
     */
    public void setValue(Object property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     * @return bean对象
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * 构造Bean
     * @param propertyMap   属性map集合
     * @return  Bean
     */
    private Object generateBean(Map<String, Class> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        for (Map.Entry<String, Class> me : propertyMap.entrySet()) {
            // 给bean添 属性;即属性对应的类型;
            generator.addProperty(me.getKey(), me.getValue());
        }
        return generator.create();
    }

}
