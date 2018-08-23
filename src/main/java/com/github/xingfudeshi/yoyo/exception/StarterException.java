/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: StarterException
 * Author:   Wu
 * Date:     2018/8/7 9:13
 * Description: 自定义rocketmq异常
 * History:
 */
package com.github.xingfudeshi.yoyo.exception;

/**
 * 〈自定义rocketmq异常〉
 *
 * @author Wu
 * @create 2018/8/16
 * @since 1.0.0
 */
public class StarterException extends Exception {

    public StarterException() {
        super();
    }

    public StarterException(String message) {
        super(message);
    }

    public StarterException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarterException(Throwable cause) {
        super(cause);
    }

    protected StarterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
