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
 * 2015��7��1��
 */
public class MessageUtil {

	/**
	 * ������Ϣ���ͳ���
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
	// xmlתΪ����
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

	// ���ı���Ϣ��ת��Ϊxml
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	//ƴ�ӷ�����Ϣ
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return MessageUtil.textMessageToXml(text);
	}
	
	
	//���˵�
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ��Ĺ�ע���밴�ղ˵���ʾ ���в���:\n\n");
		sb.append("1��ϵͳ����\n");
		sb.append("2����ϸ�ӹ��ܽ���\n]n");
		sb.append("�ظ� �� �����˲˵�");
		return sb.toString();
		
	}
	//1ϵͳ����
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("������ϵͳ�ս��ܰ�ť");
		return sb.toString();
	}
	//2.��ϸ����
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("��������ϸ�ӹ��ܽ���  ��ť");
		return sb.toString();
	}
	//����access_token expires_in
	public static  AccessToken gettokenandexp(){
		AccessToken atk= new AccessToken();
		JSONObject jsonObj=WeixinUtil.httpRequest(MESSAGE_GET_TOKEN_URL, "GET",null);
		atk.setToken(jsonObj.getString("access_token"));
		atk.setExpiresIn(jsonObj.getString("expires_in"));
		return atk;
	}

	
}
