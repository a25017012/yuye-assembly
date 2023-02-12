package com.yuye.metadata.annotation.registrar;

import com.yuye.metadata.annotation.MetadataModel;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author: xgf
 * @date: 2023/1/30 15:48
 */
public class FindMetadataServiceClassPathScanHandle extends ClassPathBeanDefinitionScanner {

    public FindMetadataServiceClassPathScanHandle(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        addIncludeFilter(new AnnotationTypeFilter(MetadataModel.class));
        //调用spring的扫描
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        return beanDefinitionHolders;
    }
}