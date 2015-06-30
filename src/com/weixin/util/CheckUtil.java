package com.weixin.util;

import java.util.Arrays;

import com.weixin.decript.DecriptTest;

public class CheckUtil {
	private static final String token="weixintest";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{token,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		//sha1 加密
		String temp=DecriptTest.SHA1(content.toString());
		//返回比较结果
		return temp.equals(signature);
		
	}
}
