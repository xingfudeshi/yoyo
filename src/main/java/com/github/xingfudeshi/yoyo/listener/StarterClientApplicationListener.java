/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: StarterApplicationListener
 * Author:   Wu
 * Date:     2018/8/16 17:16
 * Description:
 * History:
 */
package com.github.xingfudeshi.yoyo.listener;

import com.github.xingfudeshi.yoyo.annotation.EnableStarterClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.io.File;

/**
 *
 * @author Wu
 * @create 2018/8/20
 * @since 1.0.0
 */
public class StarterClientApplicationListener implements ApplicationListener<ApplicationEvent> {
    private EnableStarterClient enableStarterClient;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public StarterClientApplicationListener(EnableStarterClient enableStarterClient){
         this.enableStarterClient= enableStarterClient;

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event){
        if (event instanceof ApplicationReadyEvent){
            logger.info("ready....");
            String lock=StringUtils.isBlank(enableStarterClient.lock())?enableStarterClient.value():enableStarterClient.lock();
            logger.info("creating lock file:{}",lock);
            try {
                FileUtils.write(new File(lock),String.valueOf(event.getTimestamp()));

            }catch (Exception e){
                e.printStackTrace();

            }

        }

    }
}
