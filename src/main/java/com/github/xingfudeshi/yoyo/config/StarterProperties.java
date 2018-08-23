/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: StarterProperties
 * Author:   Wu
 * Date:     2018/8/16 16:20
 * Description:
 * History:
 */
package com.github.xingfudeshi.yoyo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Wu
 * @create 2018/8/16
 * @since 1.0.0
 */
@ConfigurationProperties("yoyo.starter")
public class StarterProperties {
    private Map<String, TaskInfo> tasks = new LinkedHashMap<>();

    public Map<String, TaskInfo> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, TaskInfo> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "StarterProperties{" +
                "tasks=" + tasks +
                '}';
    }




    public static class TaskInfo implements Comparable<TaskInfo>{
        private Integer order;
        private String jar;
        private String lock;


        public String getJar() {
            return jar;
        }

        public void setJar(String jar) {
            this.jar = jar;
        }

        public String getLock() {
            return lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        @Override
        public String toString() {
            return "TaskInfo{" +
                    "order=" + order +
                    ", jar='" + jar + '\'' +
                    ", lock='" + lock + '\'' +
                    '}';
        }

        @Override
        public int compareTo(TaskInfo o) {
            if(this.order>o.getOrder()){
                return 1;
            }else if(this.order<o.getOrder()){
                return -1;

            }else{
                return 0;
            }
        }
    }
}
