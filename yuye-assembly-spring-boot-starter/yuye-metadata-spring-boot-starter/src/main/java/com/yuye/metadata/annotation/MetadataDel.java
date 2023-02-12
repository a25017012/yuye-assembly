package com.yuye.metadata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MetadataDel {

    String modelName() default "";

    /**
     * 默认可以 不填，使用元数据的 id字段
     *
     * @return
     */
    String id() default "";
}