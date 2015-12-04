//package com.haier.neusoft.cdk.common.util.xml;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.xml.bind.JAXB;
//import javax.xml.bind.JAXBException;
//
//public class JAXBTest {
//
//	public static void main(String[] args) throws FileNotFoundException, JAXBException {
//		XmlBean test1 = new XmlBean();
//		test1.setRowId("123456");
//		test1.setName("lili");
//
//		List<String> listAddr = new ArrayList<String>();
//		listAddr.add("青岛");
//		listAddr.add("大连");
//
//		test1.setListAddr(listAddr);
//
//		//把test类转化成xml文件
//		JAXBmarshal(test1, new File("c://"));
//
//		System.out.println("================转化成功=================");
//
//		//把xml文件转换成test类
//		XmlBean test2 = (XmlBean) JAXBunmarshal(new File("c://test.xml"), XmlBean.class);
//		for (int i = 0; i < test2.getListAddr().size(); i++) {
//			System.out.println(test2.getListAddr().get(i));
//		}
//
//	}
//
//	//把xml文件转化成对象
//	public static Object JAXBunmarshal(File xmlFile, Class<?> type) throws JAXBException {
//		return JAXB.unmarshal(xmlFile, type);
//	}
//
//	//把对象转化成xml文件，可以指定成outputstream,writer,string，可以参考API
//	public static void JAXBmarshal(Object obj, File fRootDir) {
//		if (!fRootDir.exists()) {
//			fRootDir.mkdirs();
//		}
//		JAXB.marshal(obj, new File(fRootDir, "test.xml"));
//	}
//
//}
