
package com.github.xingfudeshi.yoyo.exception;

/**
 * 〈自定义rocketmq异常〉
 *
 * @author Wu
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
