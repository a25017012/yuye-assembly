package com.yuye.metadata.annotation.run;

/**
 * @author: yuye
 * @date: 2023/2/2 12:55
 */

import com.yuye.metadata.annotation.config.MetadataConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * 接口实例工厂，这里主要是用于提供接口的实例对象
 *
 * @param <T>
 * @author lichuang
 */
public class ServiceFactory<T> implements FactoryBean<T> {
    private Class<T> interfaceType;

    public ServiceFactory(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        //这里主要是创建接口对应的实例，便于注入到spring容器中
        return (T) MetadataConfig.getMapper(interfaceType);
//        final MetadataMapperProxyFactory<T> mapperProxyFactory = (MetadataMapperProxyFactory<T>) MetadataConfig.knownMappers.get(interfaceType);
//        return (T) mapperProxyFactory.newInstance();
    }

    @Override
    public Class<T> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}