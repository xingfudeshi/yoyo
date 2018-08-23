package com.github.xingfudeshi.yoyo.config;



import com.github.xingfudeshi.yoyo.annotation.EnableStarter;
import com.github.xingfudeshi.yoyo.listener.StarterApplicationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

/**
 * 〈〉
 *
 * @author Wu
 * @since 1.0.0
 */

@Configuration
@EnableConfigurationProperties(value={StarterProperties.class})
public class StarterAutoConfiguration implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private StarterProperties starterProperties;

    @Bean
    public ApplicationListener<ApplicationEvent> applicationReadyEventListener(){
        return new StarterApplicationListener();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=(ConfigurableApplicationContext)applicationContext;
    }

    private EnableStarter getAnnotationBean(){
        Map<String,Object> beans =this.applicationContext.getBeansWithAnnotation(EnableStarter.class);
        EnableStarter enableStarter=null;
        if(Objects.nonNull(beans)){
            for(Map.Entry<String,Object> entry:beans.entrySet()){
                enableStarter = applicationContext.findAnnotationOnBean(entry.getKey(), EnableStarter.class);

            }
        }
        return enableStarter;

    }
}
