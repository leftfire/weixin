package com.weixin.util;

public class MenuManager {
	public static void DoCreateMenu(){
		try {
			String accessToken=MessageUtil.getAccessToken().getToken();
			
			if(MessageUtil.createMenu(getMenu(), accessToken)==0){
				 System.out.println("΢�Ų˵������ɹ�");
			}else{
				System.out.println("΢�Ų˵�����ʧ��");
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	 private static Menu getMenu() { 
	        CommonButton btn11 = new CommonButton(); 
	        btn11.setName("��������λ��ѡ����"); 
	        btn11.setType("location_select"); 
	        btn11.setKey("11"); 
	 
	        CommonButton btn12 = new CommonButton(); 
	        btn12.setName("ɨ�����¼�"); 
	        btn12.setType("scancode_push"); 
	        btn12.setKey("12"); 
	 
	        CommonButton btn13 = new CommonButton(); 
	        btn13.setName("ɨ�����¼��ҵȴ�"); 
	        btn13.setType("scancode_waitmsg"); 
	        btn13.setKey("13"); 
	 
	        CommonButton btn14 = new CommonButton(); 
	        btn14.setName("��ʷ�ϵĽ���"); 
	        btn14.setType("click"); 
	        btn14.setKey("14"); 
	 
	        CommonButton btn21 = new CommonButton(); 
	        btn21.setName("�����㲥"); 
	        btn21.setType("click"); 
	        btn21.setKey("21"); 
	 
	        CommonButton btn22 = new CommonButton(); 
	        btn22.setName("������Ϸ"); 
	        btn22.setType("click"); 
	        btn22.setKey("22"); 
	 
	        CommonButton btn23 = new CommonButton(); 
	        btn23.setName("��Ů��̨"); 
	        btn23.setType("click"); 
	        btn23.setKey("23"); 
	 
	        CommonButton btn24 = new CommonButton(); 
	        btn24.setName("����ʶ��"); 
	        btn24.setType("click"); 
	        btn24.setKey("24"); 
	 
	        CommonButton btn25 = new CommonButton(); 
	        btn25.setName("�������"); 
	        btn25.setType("click"); 
	        btn25.setKey("25"); 
	 
	        CommonButton btn31 = new CommonButton(); 
	        btn31.setName("Q��Ȧ"); 
	        btn31.setType("click"); 
	        btn31.setKey("31"); 
	 
	        CommonButton btn32 = new CommonButton(); 
	        btn32.setName("��Ӱ���а�"); 
	        btn32.setType("click"); 
	        btn32.setKey("32"); 
	 
	        ViewButton btn33 = new ViewButton(); 
	        btn33.setName("��˾����"); 
	        btn33.setType("view"); 
	        btn33.setUrl("http://www.itnice.net/");
	 
	        ComplexButton mainBtn1 = new ComplexButton(); 
	        mainBtn1.setName("��������"); 
	        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 }); 
	 
	        ComplexButton mainBtn2 = new ComplexButton(); 
	        mainBtn2.setName("������վ"); 
	        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 }); 
	 
	        ComplexButton mainBtn3 = new ComplexButton(); 
	        mainBtn3.setName("��������"); 
	        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 }); 
	 
	        /** 
	         * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br> 
	         *  
	         * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br> 
	         * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */ 
	        Menu menu = new Menu(); 
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 }); 
	 
	        return menu; 
	    } 
}
