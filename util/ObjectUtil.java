package com.haier.neusoft.o2o.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class ObjectUtil {

	/**
	 * 根据属性名获取属性值
	 * */
	private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

//	/**
//	 * 获取属性名数组
//	 * */
//	private String[] getFiledName(Object o) {
//		Field[] fields = o.getClass().getDeclaredFields();
//		String[] fieldNames = new String[fields.length];
//		for (int i = 0; i < fields.length; i++) {
//			// System.out.println(fields[i].getType());
//			fieldNames[i] = fields[i].getName();
//		}
//		return fieldNames;
//	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 * */
	@SuppressWarnings({ "rawtypes" })
	public static List<Map> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		// String[] fieldNames = new String[fields.length];
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap<String, Object>();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

//	/**
//	 * 获取对象的所有属性值，返回一个对象数组
//	 * */
//	public Object[] getFiledValues(Object o) {
//		String[] fieldNames = this.getFiledName(o);
//		Object[] value = new Object[fieldNames.length];
//		for (int i = 0; i < fieldNames.length; i++) {
//			value[i] = getFieldValueByName(fieldNames[i], o);
//		}
//		return value;
//	}

	public static String getObjectToURLString(Object obj) {
		StringBuffer sb = new StringBuffer();
		Field[] fields = obj.getClass().getDeclaredFields();
		String name;
		Object o;
		for (int i = 0; i < fields.length; i++) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			name = fields[i].getName();
			sb.append(name).append("=");
			o = getFieldValueByName(name, obj);
			if (o == null) {
				sb.append("null");// 替换所有半角逗号为全角逗号
			} else {
				o = o.toString().replaceAll(",", "，").replaceAll("&", "");// 替换所有半角逗号为全角逗号
				sb.append(o);
			}
		}
		return sb.toString();
	}

//	public static String getObjectToURLString(
//			@SuppressWarnings("rawtypes") List objlist) {
//		StringBuffer sb = new StringBuffer();
//		if (objlist == null || objlist.size() == 0) {
//			return "";
//		}
//		Object obj = objlist.get(0);
//		Field[] fields = obj.getClass().getDeclaredFields();
//		String name;
//		Object o;
//		for (int i = 0; i < fields.length; i++) {
//			if (sb.length() > 0) {
//				sb.append("&");
//			}
//			name = fields[i].getName();
//			sb.append(name).append("=");
//			for (int j = 0; j < objlist.size(); j++) {
//				o = getFieldValueByName(name, objlist.get(j));
//				if (j > 0) {
//					sb.append(",");
//				}
//				if (o == null) {
//					sb.append("null");// 替换所有半角逗号为全角逗号
//				} else {
//					o = o.toString().replaceAll(",", "，").replaceAll("&", "");// 替换所有半角逗号为全角逗号
//					sb.append(o);
//				}
//			}
//		}
//		return sb.toString();
//	}
    public static String objectToURLString(Object obj) {
        StringBuffer sb = new StringBuffer();
        Field[] fields = obj.getClass().getDeclaredFields();
        String name;
        Object o;
        for (int i = 0; i < fields.length; i++) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            name = fields[i].getName();
            sb.append(name).append("=");
            o = getFieldValueByName(name, obj);
            if (o == null) {
                sb.append("null");// 替换所有半角逗号为全角逗号
            } else {
                if(!"phone".equals(name)){
                    o = o.toString().replaceAll(",", "，").replaceAll("&", "");// 替换所有半角逗号为全角逗号
                }
                try {
                   sb.append(URLEncoder.encode((String) o, "gb2312"));
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
        }
        return sb.toString();
    }

//    public static String objectToURLStringUtf(Object obj) {
//        StringBuffer sb = new StringBuffer();
//        Field[] fields = obj.getClass().getDeclaredFields();
//        String name;
//        Object o;
//        for (int i = 0; i < fields.length; i++) {
//            if (sb.length() > 0) {
//                sb.append("&");
//            }
//            name = fields[i].getName();
//            sb.append(name).append("=");
//            o = getFieldValueByName(name, obj);
//            if (o == null) {
//                sb.append("null");// 替换所有半角逗号为全角逗号
//            } else {
//                o = o.toString().replaceAll(",", "，").replaceAll("&", "");// 替换所有半角逗号为全角逗号
//                try {
//                    sb.append(URLEncoder.encode((String) o, "utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    return null;
//                }
//            }
//        }
//        return sb.toString();
//    }
}
