package com.weixin.util;

import java.util.Arrays;

import com.weixin.decript.DecriptTest;

public class CheckUtil {
	private static final String token="weixintest";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{token,timestamp,nonce};
		//����
		Arrays.sort(arr);
		//�����ַ���
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		//sha1 ����
		String temp=DecriptTest.SHA1(content.toString());
		//���رȽϽ��
		return temp.equals(signature);
		
	}
}
