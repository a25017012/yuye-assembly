package com.yuye.metadata.annotation;

import java.lang.annotation.*;

/**
 * 放在 mapper 上面，定义mapper对应哪个元数据的属性
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface MetadataModel {

    String value() default "";

}