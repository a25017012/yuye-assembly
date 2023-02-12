package com.yuye.metadata.annotation.registrar;

import com.yuye.metadata.annotation.MetadataModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * desc:
 * author: Administrator
 * date: 2020/11/4
 */
@Configuration
public class DefinitionRegistryPostProcessor implements ImportBeanDefinitionRegistrar,BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.setBeanNameGenerator(new AnnotationBeanNameGenerator());
        // 定义需要扫描的注解 -- 自定义注解
        scanner.addIncludeFilter(new AnnotationTypeFilter(MetadataModel.class));
        // 定义扫描的包
        scanner.scan("com");
    }

    @Override
    public void postProcessBeanFactory(
        ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
