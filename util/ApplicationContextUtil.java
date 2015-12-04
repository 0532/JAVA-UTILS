package com.haier.neusoft.o2o.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 14-6-5.
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    //NOSONAR
    private static ApplicationContext applicationContext;//NOSONAR
    //NOSONAR
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //NOSONAR
        this.applicationContext =  applicationContext;//NOSONAR
        //NOSONAR
    }


    public static Object getBean(String beanId){
        return applicationContext.getBean(beanId);
    }


}
