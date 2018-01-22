package com.qcc.processor;

import com.qcc.annotation.Cache;
import com.qcc.service.AccountService;
import com.qcc.utils.CacheMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 注入@Cache
 */
@Component
public class CustomBeanDefintionRegistryPostPorcessor extends InstantiationAwareBeanPostProcessorAdapter  {



    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
       if (pds != null && pds.length > 0) {
          Field[]  fields = bean.getClass().getDeclaredFields();
          for (Field field : fields) {
              field.setAccessible(true);
             Cache cache = field.getAnnotation(Cache.class);
             if (cache != null) {
                 String space = cache.space();
                 ParameterizedTypeImpl type = (ParameterizedTypeImpl) field.getGenericType();
                 try {
                    Object o = type.getRawType().getConstructor(String.class).newInstance(space);
                    field.set(bean,o);
                 } catch (NoSuchMethodException e) {
                     e.printStackTrace();
                 } catch (IllegalAccessException e) {
                     e.printStackTrace();
                 } catch (InstantiationException e) {
                     e.printStackTrace();
                 } catch (InvocationTargetException e) {
                     e.printStackTrace();
                 }
             }
          }
       }
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
