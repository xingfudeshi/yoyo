/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: CheckLockTask
 * Author:   Wu
 * Date:     2018/8/17 9:26
 * Description:
 * History:
 */
package com.github.xingfudeshi.yoyo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * check the lock file ,will return true when the lock file created
 * @author Wu
 * @create 2018/8/17
 * @since 1.0.0
 */
public class CheckLockTask implements Callable<Boolean> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String lock;

    public CheckLockTask(String lock){
        this.lock=lock;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info("start checking lock task for:{}",lock);
        File lockFile=new File(this.lock);
        while(true){
            if(Objects.nonNull(lockFile)&&lockFile.exists()){
                break;
            }
            Thread.sleep(2000);

        }
        return true;
    }
}
