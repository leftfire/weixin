package com.weixin.access_token;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO 自动生成的方法存根

	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO 自动生成的方法存根

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO 自动生成的方法存根
		return null;
	}

}
