package com.yuye.metadata.annotation.config;

import com.yuye.metadata.annotation.override.MetadataMapperProxyFactory;
import com.yuye.metadata.annotation.registrar.DefinitionRegistryPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xgf
 * @date: 2022/10/9 17:47
 */
@Component
@Slf4j
public class MetadataConfig {

//    @Bean
//    public DefinitionRegistryPostProcessor definitionRegistryPostProcessor() {
//        return new DefinitionRegistryPostProcessor();
//    }

    public static final Map<Class<?>, MetadataMapperProxyFactory<?>> knownMappers = new HashMap<>();
    @Resource
    private ApplicationContext applicationContext;

    public static <T> T getMapper(Class<T> type) {
        addMapper(type);
        // TODO 这里换成 MybatisMapperProxyFactory 而不是 MapperProxyFactory
        final MetadataMapperProxyFactory<T> mapperProxyFactory = (MetadataMapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MybatisPlusMapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public static <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public static <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // TODO 如果之前注入 直接返回
                return;
                // TODO 这里就不抛异常了
//                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                // TODO 这里也换成 MybatisMapperProxyFactory 而不是 MapperProxyFactory
                knownMappers.put(type, new MetadataMapperProxyFactory<>(type));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }

}
