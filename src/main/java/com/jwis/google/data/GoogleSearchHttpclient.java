package com.jwis.google.data;
import java.net.URLDecoder;
import java.net.URLEncoder;




import java.util.List;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.jwis.google.model.Code;
import com.jwis.google.model.SearchDatas;
import com.jwis.util.FileUtilsPath;
import com.jwis.util.JiveGlobe;
import com.jwis.util.ResultPackaging;


public class GoogleSearchHttpclient {
	private GoogleSearchHttpclient() {
	}

	private static class JiveGlobeHolder {
		private static GoogleSearchHttpclient instance = new GoogleSearchHttpclient();
	}

	public static GoogleSearchHttpclient getInstance() {
		return JiveGlobeHolder.instance;
	}
	private static String key = FileUtilsPath.GOOGLE_KEY;
	//private static String key = "AIzaSyDGlCcdmPQ2d2mSntIrexbrncP1Z_qOI6Q";
	private static String cx = FileUtilsPath.GOOGLE_CX;
	
	public String getGooglesearchdata(String queryString,String start,String webSite){
				try {
					 HttpClient client = new HttpClient();//定义client对象
					 client.getHttpConnectionManager().getParams().setConnectionTimeout(2000);//设置连接超时时间为2秒（连接初始化时间）
					 queryString = URLEncoder.encode(queryString,"UTF-8");
//					 System.out.println("<<<<正确的<<<<"+queryString);
//					 String s = "狗";
////					 System.out.println("<<<<<测试"+queryString);
//					 System.out.println("<<<<<"+URLEncoder.encode(s,"UTF-8"));
//					 String test = new String(s.getBytes("ISO-8859-1"), "UTF-8");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test));
//					 String test1 = new String(s.getBytes("ISO-8859-1"), "GBK");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test1));
//					 String test2 = new String(s.getBytes("GBK"), "ISO-8859-1");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test2));
//					 String test3 = new String(s.getBytes("GBK"), "UTF-8");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test3));
//					 String test4 = new String(s.getBytes("UTF-8"), "ISO-8859-1");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test4));
//					 String test5 = new String(s.getBytes("UTF-8"), "GBK");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test5));
//					 String test6 = new String(s.getBytes("UTF-8"), "UTF-8");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test6));
//					 String test7 = new String(s.getBytes("GBK"), "GBK");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test7));
//					 String test8 = new String(s.getBytes("ISO-8859-1"), "ISO-8859-1");
//					 System.out.println( "<<<<<<测试狗"+URLEncoder.encode(test8));
//					 if(2>1){
//						 return "200";
//					 }
//					 URLEncoder.
//					 URLDecoder
					// System.out.println(queryString);
				//	 System.out.println("https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&start="+start+"&q="+queryString+"");
				//	 System.out.println("https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&start="+start+"&q="+queryString+"");
					 String query = "";
					 if(JiveGlobe.isEmpty(webSite)){
						 query = "https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&start="+start+"&q="+queryString+"";
					 }else{
						 query = "https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&start="+start+"&q="+queryString+"&siteSearch="+webSite+"";
					 }
					 GetMethod method = new GetMethod(query);//访问下谷歌的首页
					 int statusCode = client.executeMethod(method);//状态，一般200为OK状态，其他情况会抛出如404,500,403等错误
					if (statusCode != 200) {
						 return "200";
					}
				//	System.out.println(method.getResponseBodyAsString());//输出反馈结果						    
					client.getHttpConnectionManager().closeIdleConnections(1);
				//	System.out.println(method.getResponseBodyAsString());
					System.out.println(method.getResponseBodyAsString());
					return method.getResponseBodyAsString();
				}catch(ConnectTimeoutException e){
					 return "400";
				} catch (Exception e) {
					 return "error";
				}
	}
	
	


}
