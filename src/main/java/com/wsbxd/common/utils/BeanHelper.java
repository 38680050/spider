package com.wsbxd.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * description: Bean工厂工具类
 *
 * @author 38680
 * @version 1.0
 * @date 2018/9/20 10:25
 */
@Component
public class BeanHelper implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanHelper.beanFactory = beanFactory;
    }

    public static <T>T getBean(Class<T> type){
        return beanFactory.getBean(type);
    }
}
