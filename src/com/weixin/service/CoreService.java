package com.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.weixin.po.TextMessage;
import com.weixin.util.MenuManager;
import com.weixin.util.MessageUtil;

public class CoreService {
	public static String processRequest(HttpServletRequest request) { 
        String respMessage = null; 
        try { 
            // 默认返回的文本消息内容 
            String respContent = "请求处理异常，请稍候尝试！"; 
 
            // xml请求解析 
            Map<String, String> requestMap = MessageUtil.xmlToMap(request); 
 
            // 发送方帐号（open_id） 
            String fromUserName = requestMap.get("FromUserName"); 
            // 公众帐号 
            String toUserName = requestMap.get("ToUserName"); 
            // 消息类型 
            String msgType = requestMap.get("MsgType"); 
 
            // 回复文本消息 
            TextMessage textMessage = new TextMessage(); 
            textMessage.setToUserName(fromUserName); 
            textMessage.setFromUserName(toUserName); 
            textMessage.setCreateTime(new Date().getTime()); 
            textMessage.setMsgType(MessageUtil.MESSAGE_TEXT); 
           // textMessage.setFuncFlag(0); 
 
            // 文本消息 
            if (msgType.equals(MessageUtil.MESSAGE_TEXT)) { 
                respContent = "您发送的是文本消息！"+"内容为:"+requestMap.get("Content"); 
                if("1".equals(requestMap.get("Content"))){
                	MenuManager.DoCreateMenu();
                }
            } 
            // 图片消息 
            else if (msgType.equals(MessageUtil.MESSAGE_IMAGE)) { 
                respContent = "您发送的是图片消息！"; 
            } 
            // 地理位置消息 
            else if (msgType.equals(MessageUtil.MESSAGE_LOCATION)) { 
                respContent = "您发送的是地理位置消息！"; 
            } 
            // 链接消息 
            else if (msgType.equals(MessageUtil.MESSAGE_LINK)) { 
                respContent = "您发送的是链接消息！"; 
            } 
            // 音频消息 
            else if (msgType.equals(MessageUtil.MESSAGE_VOICE)) { 
                respContent = "您发送的是音频消息！"; 
            } 
            // 事件推送 
            else if (msgType.equals(MessageUtil.MESSAGE_EVENT)) { 
                // 事件类型 
                String eventType = requestMap.get("Event"); 
                // 订阅 
                if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) { 
                    respContent = "谢谢您的关注！"; 
                } 
                // 取消订阅 
                else if (eventType.equals(MessageUtil.MESSAGE_UNSUBSCRIBE)) { 
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息 
                } 
                // 自定义菜单点击事件 
                else if (eventType.equals(MessageUtil.MESSAGE_CLICK)) { 
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应 
                    String eventKey = requestMap.get("EventKey"); 
 
                    if (eventKey.equals("11")) { 
                        respContent = "地理位置选择器被点击！"; 
                    } else if (eventKey.equals("12")) { 
                        respContent = "扫码推事件被点击！"; 
                    } else if (eventKey.equals("13")) { 
                        respContent = "扫码推事件且等待被点击！"; 
                    } else if (eventKey.equals("14")) { 
                        respContent = "历史上的今天菜单项被点击！"; 
                    } else if (eventKey.equals("21")) { 
                        respContent = "歌曲点播菜单项被点击！"; 
                    } else if (eventKey.equals("22")) { 
                        respContent = "经典游戏菜单项被点击！"; 
                    } else if (eventKey.equals("23")) { 
                        respContent = "美女电台菜单项被点击！"; 
                    } else if (eventKey.equals("24")) { 
                        respContent = "人脸识别菜单项被点击！"; 
                    } else if (eventKey.equals("25")) { 
                        respContent = "聊天唠嗑菜单项被点击！"; 
                    } else if (eventKey.equals("31")) { 
                        respContent = "Q友圈菜单项被点击！"; 
                    } else if (eventKey.equals("32")) { 
                        respContent = "电影排行榜菜单项被点击！"; 
                    } else if (eventKey.equals("33")) { 
                        respContent = "幽默笑话菜单项被点击！"; 
                    } 
                } 
            } 
 
            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return respMessage;
	}
}
