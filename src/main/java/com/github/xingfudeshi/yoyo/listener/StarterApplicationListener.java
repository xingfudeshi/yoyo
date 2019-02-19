
package com.github.xingfudeshi.yoyo.listener;


import com.github.xingfudeshi.yoyo.config.StarterProperties;
import com.github.xingfudeshi.yoyo.task.CheckLockTask;
import com.github.xingfudeshi.yoyo.task.StartTask;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *
 * @author Wu
 * @since 1.0.0
 */
public class StarterApplicationListener implements ApplicationListener<ApplicationEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StarterProperties starterProperties;

    @Override
    public void onApplicationEvent(ApplicationEvent event){
        if (event instanceof ApplicationPreparedEvent){
            logger.info("prepared....");
            //delete all .lock files
            try {
                String path=new File("").getAbsolutePath();
                logger.info("work folder:{}",path);
                Collection<File> files =FileUtils.listFiles(new File(path),new String[]{"lock"},true);
                if(Objects.nonNull(files)){
                    logger.info("found :{} lock file",files.size());
                    for( File f:files){
                        logger.info("deleting :{},result:{}",f.getAbsolutePath(),FileUtils.deleteQuietly(f));

                    }
                }
                //kill running task with pid
                Collection<File> pidFiles =FileUtils.listFiles(new File(path),new String[]{"pid"},true);
                if(Objects.nonNull(pidFiles)){
                    logger.info("found :{} pid file",pidFiles.size());
                    for( File pidfile:pidFiles){
                        String fileName=pidfile.getName();
                        String pid=fileName.substring(0,fileName.lastIndexOf("."));
                        logger.info("trying to kill pid:{}",pid);
                        Runtime.getRuntime().exec(new String []{"kill","-9",pid});
                        logger.info("deleting pid file :{},result:{}",pidfile.getAbsolutePath(),FileUtils.deleteQuietly(pidfile));

                    }
                }


            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);

            }
        }
        else if (event instanceof ApplicationReadyEvent) {
            logger.info("ready....");
            Map<String,StarterProperties.TaskInfo> tasks =starterProperties.getTasks();
            LinkedList<StarterProperties.TaskInfo> taskInfoArrayList=new LinkedList<>(tasks.values());
            Collections.sort(taskInfoArrayList);
            LinkedList<Future<Boolean>> futureTaskList=new LinkedList<>();
            logger.info("process tasks....");
            try {

                ExecutorService pool=new ThreadPoolExecutor(2,5,20,TimeUnit.SECONDS,new LinkedBlockingQueue());

                while(taskInfoArrayList.size()>0){
                    StarterProperties.TaskInfo taskInfo =taskInfoArrayList.remove();
                    //find all task with the same order
                    LinkedList<StarterProperties.TaskInfo> sameOrderTaskInfos=new LinkedList<>(taskInfoArrayList.stream().filter(ti->ti.getOrder().equals(taskInfo.getOrder())).collect(Collectors.toList()));
                    sameOrderTaskInfos.add(taskInfo);
                    logger.info("found {} tasks with same order:{}",sameOrderTaskInfos.size(),taskInfo.getOrder());

                    while(sameOrderTaskInfos.size()>0){
                        StarterProperties.TaskInfo task =sameOrderTaskInfos.remove();
                        File startFile=new File(task.getJar());
                        //will skip this task if the jar file does not exist.
                        if(Objects.nonNull(startFile)&&startFile.exists()){
                            StartTask startTask=new StartTask(task.getJar());
                            CheckLockTask checkLockTask=new CheckLockTask(task.getLock());
                            //FutureTask<Boolean> futureTask=new FutureTask<>(checkLockTask);
                            pool.execute(startTask);
                            futureTaskList.add(pool.submit(checkLockTask));
                        }else{
                            continue;
                        }
                    }
                    //check for task starting
                    while(futureTaskList.size()>0){
                        if(futureTaskList.getFirst().get()){
                            futureTaskList.removeFirst();
                        }
                    }
                    logger.info("Tasks with same order:{} has been started",taskInfo.getOrder());
                }
                pool.shutdown();
                logger.info("All tasks has been started");

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
