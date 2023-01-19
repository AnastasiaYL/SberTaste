package com.example.sbertaste.configuration;

import com.example.sbertaste.annotation.GenericController;
import com.example.sbertaste.annotation.DtoField;
import com.example.sbertaste.annotation.EntityField;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Component
public class GenericControllerAnnotationProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        configureFieldInjection(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        return bean;
    }

    private void configureFieldInjection(Object bean) {
        Class<?> beanClass = bean.getClass();
        Annotation genericControllerAnnotation = AnnotationUtils.findAnnotation(beanClass, GenericController.class);
        if (genericControllerAnnotation != null) {
            ReflectionUtils.FieldCallback fieldCallback = field -> {
                Type[] genericTypes = ((ParameterizedType) beanClass.getGenericSuperclass()).getActualTypeArguments();

                if (field.isAnnotationPresent(EntityField.class)) {
                    setField(bean, field, genericTypes[0]);
                } else if (field.isAnnotationPresent(DtoField.class)) {
                    setField(bean, field, genericTypes[1]);
                }
            };

            ReflectionUtils.doWithFields(beanClass, fieldCallback);
        }
    }

    private void setField(Object bean, Field field, Type genericType) throws IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        field.set(bean, genericType);
    }
}
