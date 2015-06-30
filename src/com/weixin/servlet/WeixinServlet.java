package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.weixin.po.TextMessage;
import com.weixin.util.CheckUtil;
import com.weixin.util.MessageUtil;

public class WeixinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String echostr=req.getParameter("echostr");
	PrintWriter out=resp.getWriter();	
	if(CheckUtil.checkSignature(signature, timestamp, nonce)){
		out.print(echostr.toString());
	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
		// TODO 自动生成的方法存根
		try {
			Map<String,String> map=MessageUtil.xmlToMap(req);
			String fromUserName=map.get("FromUserName");
			String toUserName=map.get("ToUserName");
			String msgType=map.get("MsgType");
			String content=map.get("Content");
			String message=null;
			if ("text".equals(msgType)){
				TextMessage text=new TextMessage();
				text.setFromUserName(toUserName);
				text.setToUserName(fromUserName);
				text.setMsgType("text");
				text.setCreateTime(new Date().getTime());
				text.setContent("您发送的微信内容是："+content);
				message=MessageUtil.textMessageToXml(text);
				
			}
			out.print(message);
		} catch (DocumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
