package com.yuye.metadata.annotation;

import com.yuye.metadata.annotation.registrar.DefinitionRegistryPostProcessor;
import com.yuye.metadata.annotation.registrar.MetadataRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 放在 application 启动类 指定扫描的包名
 *
 * @author yuye
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({MetadataRegistrar.class, DefinitionRegistryPostProcessor.class})
public @interface MetadataModelScan {

    String[] basePackage() default {};

}