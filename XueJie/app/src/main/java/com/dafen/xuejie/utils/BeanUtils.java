package com.dafen.xuejie.utils;

import java.lang.reflect.Field;

/**
 * Bean字段工具类
 *
 * @author _Ms
 */
public class BeanUtils {

    /**
     * 支持数据类型
     * @author _Ms
     */
    public enum TYPE {
        INTEGER, DOUBLE, FLOAT, SHORT, LONG, BOOLEAN, CHARACTER, BYTE, STRING
    }

    /**
     * 设置bean错误处理模式
     */
    public enum MODE {
        ERROR_PASS,
        ERROR_DEFALUT
    }

    /*
     * 8大基本数据类型默认值
     */
    private static byte mByte;
    private static short mShort;
    private static int mInt;
    private static long mLong;
    private static float mFloat;
    private static double mDouble;
    private static boolean mBoolean;
    private static char mChar;

    /**
     * 根据类字节码对象获取该类所有字段的名称
     *
     * @param beanObjectClass
     * @return null:无字段, !null,包含字段名的String数组
     */
    public static String[] fromBeanField(Class<?> beanObjectClass) {
        if (beanObjectClass == null) {
            throw new NullPointerException();
        }

        Field[] fields = beanObjectClass.getDeclaredFields();
        if (fields.length == 0) {
            return null;
        }
        String[] fieldsString = new String[fields.length];
        for (int x = 0; x < fields.length; x++) {
            fieldsString[x] = fields[x].getName();
        }

        return fieldsString;
    }

    /**
     * 根据字段名、bean对象，获取bean对象中的该字段的值(Object)
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object fromBeanField(Object beanObj, String fieldName)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Object fieldValue = null;

        // 方法使用判断
        if (beanObj == null) {
            throw new NullPointerException();
        }
        if (fieldName == null || fieldName.length() == 0) {
            throw new NullPointerException();
        }

        // 获取字段对象
        Field field = beanObj.getClass().getDeclaredField(fieldName);

        // 跳过语法检查
        field.setAccessible(true);

        // 获取字段值
        fieldValue = field.get(beanObj);

        // 返回结果
        return fieldValue;
    }

    /**
     * 设置字符串值转换到bean field
     * @param mode
     * @param beanObj
     * @param fieldName
     * @param fieldValue
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void toBeanField(MODE mode, Object beanObj, String fieldName, String fieldValue) throws NoSuchFieldException, IllegalAccessException {

        // 获取字段对象
        Field field = beanObj.getClass().getDeclaredField(fieldName);

        // 跳过语法检查
        field.setAccessible(true);

        // 获取字段数据类型
        Class<?> fieldClass = field.getType();

		/*
		 * 获取类型
		 */
        TYPE fieldType = getType(fieldClass);

		/*
		获取至指定类型的转换值
		 */
        Object convertValue;
        try {
            convertValue = convert(fieldValue, fieldType);
        } catch(Exception e) {

            switch (mode) {
                case ERROR_DEFALUT: // 默认模式
                    convertValue = getDefaultValue(fieldType);
                    break;
                case ERROR_PASS: // 跳过模式
                default:
                    return;
            }
        }

        toBeanField(beanObj, fieldName, convertValue);
    }

    /**
     * 获取指定类型的基本数据类型
     * @param type
     * @return
     */
    private static Object getDefaultValue(TYPE type) {
        switch (type) {
            case BOOLEAN:
                return mBoolean;
            case BYTE:
                return mByte;
            case CHARACTER:
                return mChar;
            case DOUBLE:
                return mDouble;
            case FLOAT:
                return mFloat;
            case INTEGER:
                return mInt;
            case LONG:
                return mLong;
            case SHORT:
                return mShort;
            case STRING:
            default:
                return null;
        }
    }

    /**
     * 将指定值赋给指定bean对象的指定字段
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void toBeanField(Object beanObj, String fieldName, Object fieldValue)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        CheckUtils.notNull(beanObj, fieldName);

        // 获取bean对象的字段对象
        Field field = beanObj.getClass().getDeclaredField(fieldName);

        // 跳过java语法检查
        field.setAccessible(true);

        // 设置字段值
        field.set(beanObj, fieldValue);
    }

    /**
     * 转换解析String到指定类型
     *
     * @param value
     * @param type
     * @return
     */
    private static Object convert(String value, TYPE type)  {
        CheckUtils.notNull(value);

        try {
            switch (type) {
                case BOOLEAN:
                    return Boolean.parseBoolean(value);
                case BYTE:
                    return Byte.parseByte(value);
                case CHARACTER:
                    return value.charAt(0);
                case DOUBLE:
                    return Double.parseDouble(value);
                case FLOAT:
                    return Float.parseFloat(value);
                case INTEGER:
                    return Integer.parseInt(value);
                case LONG:
                    return Long.parseLong(value);
                case SHORT:
                    return Short.parseShort(value);
                case STRING:
                    return value;
                default:
                    throw new ClassCastException("类型不属于8项基本数据类型或String");
            }

        } catch (Exception exception) {
            throw new ClassCastException(exception.toString());
        }

    }

    /**
     * 获取类型
     *
     * @param clazz
     *            clazz
     * @return
     */
    private static TYPE getType(Class clazz) {

        if (String.class == clazz) { // String
            return TYPE.STRING;
        } else if (Integer.class == clazz || int.class == clazz) { // int
            return TYPE.INTEGER;
        } else if (Double.class == clazz || double.class == clazz) { // double
            return TYPE.DOUBLE;
        } else if (Byte.class == clazz || byte.class == clazz) { // byte
            return TYPE.BYTE;
        } else if (Short.class == clazz || short.class == clazz) { // short
            return TYPE.SHORT;
        } else if (Long.class == clazz || long.class == clazz) { // long
            return TYPE.LONG;
        } else if (Float.class == clazz || float.class == clazz) { // float
            return TYPE.FLOAT;
        } else if (Character.class == clazz || char.class == clazz) { // char
            return TYPE.CHARACTER;
        } else if (Boolean.class == clazz || boolean.class == clazz) { // boolean
            return TYPE.BOOLEAN;
        } else {
            return null;
        }

    }

}
