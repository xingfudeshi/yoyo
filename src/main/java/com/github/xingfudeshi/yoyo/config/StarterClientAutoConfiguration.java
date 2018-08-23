/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: MyAutoConfiguration
 * Author:   Wu
 * Date:     2018/8/8 9:48
 * Description:
 * History:
 */
package com.github.xingfudeshi.yoyo.config;
import com.github.xingfudeshi.yoyo.annotation.EnableStarterClient;
import com.github.xingfudeshi.yoyo.listener.StarterClientApplicationListener;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

/**
 * 〈〉
 *
 * @author Wu
 * @create 2018/8/8
 * @since 1.0.0
 */

@Configuration
public class StarterClientAutoConfiguration implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ConfigurableApplicationContext applicationContext;

    @Bean
    public ApplicationListener<ApplicationEvent> applicationReadyEventListener(){
        EnableStarterClient enableStarterClient=getAnnotationBean();
        if(enableStarterClient==null||(StringUtils.isBlank(enableStarterClient.lock())&&StringUtils.isBlank(enableStarterClient.value()))){
            logger.info("please specific the lock attribute.");
            return null;
        }

        return new StarterClientApplicationListener(getAnnotationBean());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=(ConfigurableApplicationContext)applicationContext;
    }

    private EnableStarterClient getAnnotationBean(){
        Map<String,Object> beans =this.applicationContext.getBeansWithAnnotation(EnableStarterClient.class);
        EnableStarterClient enableStarterClient=null;
        if(Objects.nonNull(beans)){
            for(Map.Entry<String,Object> entry:beans.entrySet()){
                enableStarterClient = applicationContext.findAnnotationOnBean(entry.getKey(), EnableStarterClient.class);

            }
        }
        return enableStarterClient;

    }
}
