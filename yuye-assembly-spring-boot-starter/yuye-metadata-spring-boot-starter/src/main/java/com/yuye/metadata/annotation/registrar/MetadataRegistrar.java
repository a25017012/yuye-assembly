package com.yuye.metadata.annotation.registrar;

import com.yuye.metadata.annotation.MetadataModel;
import com.yuye.metadata.annotation.MetadataModelScan;
import com.yuye.metadata.annotation.run.ServiceFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author: xgf
 * @date: 2023/1/30 15:46
 */
public class MetadataRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    ResourceLoader resourceLoader;

    public static Set<String> listenModelNames = new HashSet<>();
    /**
     * 扫描到的 所有 元数据Model 对象Map
     */
    public static Map<String, Class> metaDataClassMap = new HashMap<String, Class>();

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private final String RESOURCE_PATTERN = "/**/*.class";
    private ApplicationContext applicationContext;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //获取所有注解的属性和值
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MetadataModelScan.class.getName()));

        if (mapperScanAttrs == null) {
            return;
        }

        //获取到basePackage的值
        String[] basePackages = mapperScanAttrs.getStringArray("basePackage");
        for (String basePackage : basePackages) {
            scanMetadataPackage(basePackage);
        }

        //遍历class map 进行接口的重用
        metaDataClassMap.forEach((k,beanClazz) -> {
//            Object  beanImpl = MetadataConfig.getMapper(beanClazz);

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);

            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            definition.setPrimary(true);
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClazz);
            //注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
            // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
            // 其返回的是该工厂Bean的getObject方法所返回的对象。
            definition.setBeanClass(ServiceFactory.class);
        //ApplicationContextUtil.getBean("com.thunisoft.ninelaw.b.metadata.mapper.TestMapper")
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            //beanClazz.getPackage().getName() + "."
//            registry.registerBeanDefinition(StringUtils.toLowerCase(beanClazz.getSimpleName()) + "Metadata", definition);
            registry.registerBeanDefinition(beanClazz.getSimpleName().toLowerCase(Locale.ROOT), definition);
//            registry.registerBeanDefinition(beanClazz.getPackage().getName() + "." + beanClazz.getSimpleName(), definition);
        });

    }

    private void scanMetadataPackage(String basePackage) {

        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(basePackage) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                MetadataModel anno = clazz.getAnnotation(MetadataModel.class);
                if (anno != null) {
                    //将注解中的类型值作为key，对应的类作为 value
                    metaDataClassMap.put(classname, clazz);
                    listenModelNames.add(anno.value());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }
}