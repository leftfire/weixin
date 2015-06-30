package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.po.TextMessage;

public class MessageUtil {
	
	//xml转为集合
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException,DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	//将文本消息，转换为xml
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream=new XStream();
		return xstream.toXML(textMessage);
	}
}
