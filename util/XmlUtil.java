package com.haier.neusoft.o2o.common.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
/**
 * 方便转换xml实体类与字符串互相转换.
 * 工具类
 * @author 李春晖
 */
public class XmlUtil {

	//xml字符串转化成对象
	public static Object jAXBunmarshalString(String xml, Class<?> type) throws JAXBException {
		return JAXB.unmarshal(new StringReader(xml), type);
	}

	//对象转化成xml字符串
	public static String jAXBmarshalString(Object obj) {
		StringWriter stringWriter = new StringWriter();
		JAXB.marshal(obj, stringWriter);
		return stringWriter.toString();
	}

	//xml文件转化成对象
	public static Object jAXBunmarshal(File xmlFile, Class<?> type) throws JAXBException {
		return JAXB.unmarshal(xmlFile, type);
	}

	//对象转化成xml文件，可以指定成outputstream,writer,string，可以参考API   
//	public static void jAXBmarshal(Object obj, File fRootDir) {
//		if (!fRootDir.exists()) {
//			fRootDir.mkdirs();
//		}
//		JAXB.marshal(obj, new File(fRootDir, "test.xml"));
//	}

}
