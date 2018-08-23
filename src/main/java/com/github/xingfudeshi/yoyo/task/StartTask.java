
package com.github.xingfudeshi.yoyo.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈〉
 *
 * @author Wu
 * @since 1.0.0
 */
public class StartTask implements Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String jar;

    public StartTask(String jar){
        this.jar=jar;
    }
    @Override
    public void run() {
        try {
            logger.info("starting jar:{}",this.jar);
            Runtime.getRuntime().exec(new String []{"/bin/sh","-c","nohup java -jar "+this.jar+" > /dev/null 2>&1 & echo $! > $!.pid"});


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
