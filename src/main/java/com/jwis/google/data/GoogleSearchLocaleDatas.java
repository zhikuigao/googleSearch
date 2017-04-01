package com.jwis.google.data;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class GoogleSearchLocaleDatas {
	public static void main(String[] args) {
		try {
			 HttpClient client = new HttpClient();//定义client对象
			 client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);//设置连接超时时间为2秒（连接初始化时间）
//			 URLEncoder.
//			 URLDecoder
			// System.out.println(queryString);
		//	 System.out.println("https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&start="+start+"&q="+queryString+"");
		//	 System.out.println("https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&start="+start+"&q="+queryString+"");
			 GetMethod method = new GetMethod("http://47.89.29.77:8080/googleSearch/data.do?start=1&queryString=猫");//访问下谷歌的首页
			 int statusCode = client.executeMethod(method);//状态，一般200为OK状态，其他情况会抛出如404,500,403等错误
			if (statusCode != 200) {
			}
		//	System.out.println(method.getResponseBodyAsString());//输出反馈结果						    
			client.getHttpConnectionManager().closeIdleConnections(1);
		//	System.out.println(method.getResponseBodyAsString());
			System.out.println( method.getResponseBodyAsString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
