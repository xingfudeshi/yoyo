
package com.github.xingfudeshi.yoyo.annotation;

import com.github.xingfudeshi.yoyo.config.StarterClientAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 〈〉
 *
 * @author Wu
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(StarterClientAutoConfiguration.class)
@Documented
public @interface EnableStarterClient {
    @AliasFor("lock")
    String value() default "";
    @AliasFor("value")
    String lock() default "";

}
