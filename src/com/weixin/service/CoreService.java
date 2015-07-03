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
            // Ĭ�Ϸ��ص��ı���Ϣ���� 
            String respContent = "�������쳣�����Ժ��ԣ�"; 
 
            // xml������� 
            Map<String, String> requestMap = MessageUtil.xmlToMap(request); 
 
            // ���ͷ��ʺţ�open_id�� 
            String fromUserName = requestMap.get("FromUserName"); 
            // �����ʺ� 
            String toUserName = requestMap.get("ToUserName"); 
            // ��Ϣ���� 
            String msgType = requestMap.get("MsgType"); 
 
            // �ظ��ı���Ϣ 
            TextMessage textMessage = new TextMessage(); 
            textMessage.setToUserName(fromUserName); 
            textMessage.setFromUserName(toUserName); 
            textMessage.setCreateTime(new Date().getTime()); 
            textMessage.setMsgType(MessageUtil.MESSAGE_TEXT); 
           // textMessage.setFuncFlag(0); 
 
            // �ı���Ϣ 
            if (msgType.equals(MessageUtil.MESSAGE_TEXT)) { 
                respContent = "�����͵����ı���Ϣ��"+"����Ϊ:"+requestMap.get("Content"); 
                if("1".equals(requestMap.get("Content"))){
                	MenuManager.DoCreateMenu();
                }
            } 
            // ͼƬ��Ϣ 
            else if (msgType.equals(MessageUtil.MESSAGE_IMAGE)) { 
                respContent = "�����͵���ͼƬ��Ϣ��"; 
            } 
            // ����λ����Ϣ 
            else if (msgType.equals(MessageUtil.MESSAGE_LOCATION)) { 
                respContent = "�����͵��ǵ���λ����Ϣ��"; 
            } 
            // ������Ϣ 
            else if (msgType.equals(MessageUtil.MESSAGE_LINK)) { 
                respContent = "�����͵���������Ϣ��"; 
            } 
            // ��Ƶ��Ϣ 
            else if (msgType.equals(MessageUtil.MESSAGE_VOICE)) { 
                respContent = "�����͵�����Ƶ��Ϣ��"; 
            } 
            // �¼����� 
            else if (msgType.equals(MessageUtil.MESSAGE_EVENT)) { 
                // �¼����� 
                String eventType = requestMap.get("Event"); 
                // ���� 
                if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) { 
                    respContent = "лл���Ĺ�ע��"; 
                } 
                // ȡ������ 
                else if (eventType.equals(MessageUtil.MESSAGE_UNSUBSCRIBE)) { 
                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ 
                } 
                // �Զ���˵�����¼� 
                else if (eventType.equals(MessageUtil.MESSAGE_CLICK)) { 
                    // �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ 
                    String eventKey = requestMap.get("EventKey"); 
 
                    if (eventKey.equals("11")) { 
                        respContent = "����λ��ѡ�����������"; 
                    } else if (eventKey.equals("12")) { 
                        respContent = "ɨ�����¼��������"; 
                    } else if (eventKey.equals("13")) { 
                        respContent = "ɨ�����¼��ҵȴ��������"; 
                    } else if (eventKey.equals("14")) { 
                        respContent = "��ʷ�ϵĽ���˵�������"; 
                    } else if (eventKey.equals("21")) { 
                        respContent = "�����㲥�˵�������"; 
                    } else if (eventKey.equals("22")) { 
                        respContent = "������Ϸ�˵�������"; 
                    } else if (eventKey.equals("23")) { 
                        respContent = "��Ů��̨�˵�������"; 
                    } else if (eventKey.equals("24")) { 
                        respContent = "����ʶ��˵�������"; 
                    } else if (eventKey.equals("25")) { 
                        respContent = "������ྲ˵�������"; 
                    } else if (eventKey.equals("31")) { 
                        respContent = "Q��Ȧ�˵�������"; 
                    } else if (eventKey.equals("32")) { 
                        respContent = "��Ӱ���а�˵�������"; 
                    } else if (eventKey.equals("33")) { 
                        respContent = "��ĬЦ���˵�������"; 
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
