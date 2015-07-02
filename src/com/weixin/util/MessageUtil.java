package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.access_token.AccessToken;
import com.weixin.access_token.WeixinUtil;
import com.weixin.po.TextMessage;

import net.sf.json.JSONObject;


/**
 * @author think
 *TODO
 * 2015年7月1日
 */
public class MessageUtil {

	/**
	 * 定义消息类型常量
	 */
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOCIE = "vocie";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	
	
	public static String MESSAGE_GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb06c74685b90aee7&secret=3d65e2d676224b8a74be005714cd247f";
	// xml转为集合
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
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

	// 将文本消息，转换为xml
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	//拼接返回消息
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return MessageUtil.textMessageToXml(text);
	}
	
	
	//主菜单
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎你的关注，请按照菜单提示 进行操作:\n\n");
		sb.append("1、系统介绍\n");
		sb.append("2、详细子功能介绍\n]n");
		sb.append("回复 ？ 调出此菜单");
		return sb.toString();
		
	}
	//1系统介绍
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("你点击了系统刚介绍按钮");
		return sb.toString();
	}
	//2.详细功能
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("你点击了详细子功能介绍  按钮");
		return sb.toString();
	}
	//返回access_token expires_in
	public static  AccessToken gettokenandexp(){
		AccessToken atk= new AccessToken();
		JSONObject jsonObj=WeixinUtil.httpRequest(MESSAGE_GET_TOKEN_URL, "GET",null);
		atk.setToken(jsonObj.getString("access_token"));
		atk.setExpiresIn(jsonObj.getString("expires_in"));
		return atk;
	}

	
}
