/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: EnableStarter
 * Author:   Wu
 * Date:     2018/8/8 13:31
 * Description:
 * History:
 */
package com.github.xingfudeshi.yoyo.annotation;



import com.github.xingfudeshi.yoyo.config.StarterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 〈〉
 *
 * @author Wu
 * @create 2018/8/16
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(StarterAutoConfiguration.class)
@Documented
public @interface EnableStarter {

}
