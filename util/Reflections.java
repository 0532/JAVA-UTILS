package com.haier.neusoft.o2o.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by pc on 14-6-12.
 */
public final class Reflections {

        public static final String CGLIB_CLASS_SEPARATOR = "$$";
        private static Logger logger = LoggerFactory.getLogger(Reflections.class);

        private Reflections() {}

        public static Object invokeGetter(Object obj, String propertyName)
        {
            String getterMethodName = "get" + StringUtils.capitalize(propertyName);
            return invokeMethod(obj, getterMethodName, new Class[0], new Object[0]);
        }

        public static void invokeSetter(Object obj, String propertyName, Object value)
        {
            invokeSetter(obj, propertyName, value, null);
        }

        public static void invokeSetter(Object obj, String propertyName, Object value, Class<?> propertyType)
        {
            Class<?> type = propertyType != null ? propertyType : value.getClass();
            String setterMethodName = "set" + StringUtils.capitalize(propertyName);
            invokeMethod(obj, setterMethodName, new Class[] { type }, new Object[] { value });
        }

        public static Object getFieldValue(Object obj, String fieldName)
        {
            Field field = getAccessibleField(obj, fieldName);
            if (field == null) {
                throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
            }
            Object result = null;
            try
            {
                result = field.get(obj);
            }
            catch (IllegalAccessException e)
            {
                logger.error("不可能抛出的异常{}", e.toString());
            }
            return result;
        }

        public static void setFieldValue(Object obj, String fieldName, Object value)
        {
            Field field = getAccessibleField(obj, fieldName);
            if (field == null) {
                throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
            }
            try
            {
                field.set(obj, value);
            }
            catch (IllegalAccessException e)
            {
                logger.error("不可能抛出的异常:{}", e.getMessage());
            }
        }

        public static Class<?> getUserClass(Class<?> clazz)
        {
            if ((clazz != null) && (clazz.getName().contains("$$")))
            {
                Class<?> superClass = clazz.getSuperclass();
                if ((superClass != null) && (!Object.class.equals(superClass))) {
                    return superClass;
                }
            }
            return clazz;
        }

        public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args)
        {
            Method method = getAccessibleMethod(obj, methodName, parameterTypes);
            if (method == null) {
                throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
            }
            try
            {
                return method.invoke(obj, args);
            }
            catch (Exception e)
            {
                throw convertReflectionExceptionToUnchecked(e);
            }
        }

        public static Field getAccessibleField(Object obj, String fieldName)
        {
            Validate.notNull(obj, "object can't be null", new Object[0]);
            Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
            for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
                try
                {
                    Field field = superClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    return field;
                }
                catch (NoSuchFieldException e) {}
            }
            return null;
        }

        public static Method getAccessibleMethod(Object obj, String methodName, Class<?>... parameterTypes)
        {
            Validate.notNull(obj, "object can't be null", new Object[0]);
            for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
                try
                {
                    Method method = superClass.getDeclaredMethod(methodName, parameterTypes);

                    method.setAccessible(true);

                    return method;
                }
                catch (NoSuchMethodException e) {}
            }
            return null;
        }

        public static <T> Class<T> getSuperClassGenricType(Class clazz)
        {
            return getSuperClassGenricType(clazz, 0);
        }

        public static Class getSuperClassGenricType(Class clazz, int index)
        {
            Type genType = clazz.getGenericSuperclass();
            if (!(genType instanceof ParameterizedType))
            {
                logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
                return Object.class;
            }
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if ((index >= params.length) || (index < 0))
            {
                logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);

                return Object.class;
            }
            if (!(params[index] instanceof Class))
            {
                logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                return Object.class;
            }
            return (Class)params[index];
        }

        public static RuntimeException convertReflectionExceptionToUnchecked(Exception e)
        {
            if (((e instanceof IllegalAccessException)) || ((e instanceof IllegalArgumentException)) || ((e instanceof NoSuchMethodException))) {
                return new IllegalArgumentException(e);
            }
            if ((e instanceof InvocationTargetException)) {
                return new RuntimeException(((InvocationTargetException)e).getTargetException());
            }
            if ((e instanceof RuntimeException)) {
                return (RuntimeException)e;
            }
            return new RuntimeException("Unexpected Checked Exception.", e);
        }


}
