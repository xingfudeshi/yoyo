package com.github.xingfudeshi.yoyo.annotation;



import com.github.xingfudeshi.yoyo.config.StarterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 〈〉
 *
 * @author Wu
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(StarterAutoConfiguration.class)
@Documented
public @interface EnableStarter {

}
