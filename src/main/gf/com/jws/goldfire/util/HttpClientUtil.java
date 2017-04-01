package com.jws.goldfire.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;




import com.jws.goldfire.constant.Constants;

import net.sf.json.JSONObject;


public class HttpClientUtil {
	
	private static final int HTTP_TIMEOUTTIME = 360000;// 联网超时时间
    private static final int HTTP_TIMESOOUT = 360000;// 服务器超时时间设置
	
	private static final String charset  = "utf-8";
	private static DefaultHttpClient mHttpClient;
	private static String mResult = "";
	
	private static boolean isRedirect = false;
	private static String preURL;
	private static HashMap<String, String> preHeaders, preParamsMap, preFilesMap;
//	private static JsonObject preParamsJson;
	private static JSONObject preParamsJson;
	
	private static int stateCode;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("deprecation")
	public static DefaultHttpClient initDefaultHttpClient() {
		try {
			if (mHttpClient == null) {
		        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
		        HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, true);
		        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, HTTP_TIMEOUTTIME);// 连接超时设置
		        HttpConnectionParams.setSoTimeout(localBasicHttpParams, HTTP_TIMESOOUT);// 服务器超时设置
		        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 16384);
		        mHttpClient = new DefaultHttpClient(localBasicHttpParams);
		        mHttpClient.setRedirectHandler(new RedirectHandler() {
		            @Override
		            public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
		                return false;
		            }
		            @Override
		            public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
		                return null;
		            }
		        });
			}
		} catch (Exception e) {
			System.out.println("【goldfire初始化失败】"+e);
			
		}
		
		return mHttpClient;
    }
	
	/**
	 * @param url			请求url
	 * @param headers		请求header
	 * @param paramsMap		请求的表单参数
	 * @param filesMap		请求的文件参数
	 * @param paramsJson	请求的json参数
	 */
//	public static int doPost(String url, HashMap<String, String> headers, 
//			HashMap<String, String> paramsMap, HashMap<String, String> filesMap, JsonObject paramsJson) {
//				
//		initDefaultHttpClient();
//		
//		if (!isRedirect) {
//			preURL = url;
//			preHeaders = headers;
//			preParamsMap = paramsMap;
//			preFilesMap = filesMap;
//			preParamsJson = paramsJson;
//		}
//		isRedirect = false;
//		
//		HttpPost httpRequest = null;
//		httpRequest = new HttpPost(url);
//
//		// 设置header
//		if (headers != null) {
//			Iterator<Entry<String, String>> headersIterator = headers.entrySet().iterator();  
//			while (headersIterator.hasNext()) {  
//			    Entry<String, String> entry = headersIterator.next();
//			    httpRequest.addHeader(entry.getKey(), entry.getValue());
//			}
//		}
//		// 设置cookie
//		try {
//			httpRequest.addHeader("Cookie", FileOperation.readTxtFile(Consts.FILE_NAME).trim());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 设置参数
//		try {
//			if (paramsMap != null) {
//				httpRequest.setHeader("Accept",
//			            "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
//			            + "application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, "
//			            + "application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, "
//			            + "application/vnd.ms-powerpoint, application/msword,application/x-www-form-urlencoded, */*");
//				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//				Iterator<Entry<String, String>> paramsIterator = paramsMap.entrySet().iterator();  
//				while (paramsIterator.hasNext()) {  
//				    Entry<String, String> entry = paramsIterator.next();
//				    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//				}
//				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, charset);
//				httpRequest.setEntity(formEntity);
//			} else if (paramsJson != null) {
//				httpRequest.addHeader("Content-Type", "application/json");
//				StringEntity stringEntity = new StringEntity(paramsJson.toString(), charset);
//				httpRequest.setEntity(stringEntity);
//			} else if (filesMap != null) {
//				MultipartEntity multipartEntity = new MultipartEntity();
//			    httpRequest.addHeader("enctype", "multipart/form-data");
//			    Iterator<Entry<String, String>> fileIterator = filesMap.entrySet().iterator();  
//				while (fileIterator.hasNext()) {
//					Entry<String, String> entry = fileIterator.next();
//					String key = entry.getKey();
//			        String value = entry.getValue();                
//			        if (key.equalsIgnoreCase("files[]")) {
//			            File file = new File(value);
//			            FileBody mFileBody = new FileBody(file, "image/jpg");
//			            multipartEntity.addPart(key, mFileBody);
//			        } else {
//			            try {
//			                StringBody stringBody = new StringBody(value);
//			                multipartEntity.addPart(key, stringBody);
//			            } catch (UnsupportedEncodingException e) {
//			                e.printStackTrace();
//			            }
//			        }
//				}
//				httpRequest.setEntity(multipartEntity);
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		// 处理请求结果
//		try {
//			HttpResponse httpResponse = mHttpClient.execute(httpRequest);
//			HttpEntity mHttpEntity = httpResponse.getEntity();
//			stateCode = httpResponse.getStatusLine().getStatusCode();
//            mResult = EntityUtils.toString(mHttpEntity, charset);
//            System.out.println("stateCode : " + stateCode + "\n" + mResult);
//			if(url.contains("login")){
//                StringBuilder cookieStr = new StringBuilder();
//                Header[] responseHeaders = httpResponse.getAllHeaders();
//                for (int i = 0; i < responseHeaders.length; i++) {
//                    if(responseHeaders[i].getName().equals("Set-Cookie")){
//                        cookieStr.append(responseHeaders[i].getValue()).append(";");
//                    }
//                }
//                FileOperation.saveCookie(cookieStr.toString(), Consts.FILE_NAME);
//            }
//			if (stateCode == 302) {
//				isRedirect = true;
//            	doPost(preURL, preHeaders, preParamsMap, preFilesMap, preParamsJson);
//            } else if(stateCode == 401 || stateCode == 307) {
//				isRedirect = true;
//            	JsonObject jsonObject = new JsonObject();
//            	jsonObject.addProperty("UserName", Consts.GOLDFIRE_NAME);
//            	jsonObject.addProperty("Password", MD5Util.getCryptedPwd(Consts.GOLDFIRE_PWD));
//            	doPost(Consts.LOGIN_URL, preHeaders, null, null, jsonObject);
//            }
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return stateCode;
//	}
	public static String doPost(String url, HashMap<String, String> headers, 
			HashMap<String, String> paramsMap, HashMap<String, String> filesMap, JSONObject paramsJson) {
				
		initDefaultHttpClient();
		
		if (!isRedirect) {
			preURL = url;
			preHeaders = headers;
			preParamsMap = paramsMap;
			preFilesMap = filesMap;
			preParamsJson = paramsJson;
		}
		isRedirect = false;
		
		HttpPost httpRequest = null;
		httpRequest = new HttpPost(url);

		// 设置header
		if (headers != null) {
			Iterator<Entry<String, String>> headersIterator = headers.entrySet().iterator();  
			while (headersIterator.hasNext()) {  
			    Entry<String, String> entry = headersIterator.next();
			    httpRequest.addHeader(entry.getKey(), entry.getValue());
			}
		}
		// 设置cookie
		try {
			httpRequest.addHeader("Cookie", FileOperation.readTxtFile(Constants.FILE_NAME).trim());
		} catch (Exception e) {
			System.out.println("【设置cookie异常】"+e);
		}
		// 设置参数
		try {
			if (paramsMap != null) {
				httpRequest.setHeader("Accept",
			            "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
			            + "application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, "
			            + "application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, "
			            + "application/vnd.ms-powerpoint, application/msword,application/x-www-form-urlencoded, */*");
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				Iterator<Entry<String, String>> paramsIterator = paramsMap.entrySet().iterator();  
				while (paramsIterator.hasNext()) {  
				    Entry<String, String> entry = paramsIterator.next();
				    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, charset);
				httpRequest.setEntity(formEntity);
			} else if (paramsJson != null) {
				httpRequest.addHeader("Content-Type", "application/json");
				StringEntity stringEntity = new StringEntity(paramsJson.toString(), charset);
				httpRequest.setEntity(stringEntity);
			} else if (filesMap != null) {
				MultipartEntity multipartEntity = new MultipartEntity();
			    httpRequest.addHeader("enctype", "multipart/form-data");
			    Iterator<Entry<String, String>> fileIterator = filesMap.entrySet().iterator();  
				while (fileIterator.hasNext()) {
					Entry<String, String> entry = fileIterator.next();
					String key = entry.getKey();
			        String value = entry.getValue();                
			        if (key.equalsIgnoreCase("files[]")) {
			            File file = new File(value);
			            FileBody mFileBody = new FileBody(file, "image/jpg");
			            multipartEntity.addPart(key, mFileBody);
			        } else {
			            try {
			                StringBody stringBody = new StringBody(value);
			                multipartEntity.addPart(key, stringBody);
			            } catch (UnsupportedEncodingException e) {
			                e.printStackTrace();
			            }
			        }
				}
				httpRequest.setEntity(multipartEntity);
			}
		} catch (UnsupportedEncodingException e) {
			  System.out.println("【gold数据异常】"+e);
		}
		
		// 处理请求结果
		try {
			HttpResponse httpResponse = mHttpClient.execute(httpRequest);
			HttpEntity mHttpEntity = httpResponse.getEntity();
			stateCode = httpResponse.getStatusLine().getStatusCode();
            mResult = EntityUtils.toString(mHttpEntity, charset);
			if(url.contains("login")){
                StringBuilder cookieStr = new StringBuilder();
                Header[] responseHeaders = httpResponse.getAllHeaders();
                for (int i = 0; i < responseHeaders.length; i++) {
                    if(responseHeaders[i].getName().equals("Set-Cookie")){
                        cookieStr.append(responseHeaders[i].getValue()).append(";");
                    }
                }
                FileOperation.saveCookie(cookieStr.toString(), Constants.FILE_NAME);
            }
			if (stateCode == 302) {
				isRedirect = true;
            	doPost(preURL, preHeaders, preParamsMap, preFilesMap, preParamsJson);
            } else if(stateCode == 401 || stateCode == 307) {
				isRedirect = true;
//            	JsonObject jsonObject = new JsonObject();
//            	jsonObject.addProperty("UserName", Consts.GOLDFIRE_NAME);
//            	jsonObject.addProperty("Password", MD5Util.getCryptedPwd(Consts.GOLDFIRE_PWD));
				JSONObject jsonObject = new JSONObject();
            	jsonObject.put("UserName", Consts.GOLDFIRE_NAME);
            	jsonObject.put("Password", MD5Util.getCryptedPwd(Consts.GOLDFIRE_PWD));
            	doPost(Consts.LOGIN_URL, preHeaders, null, null, jsonObject);
            }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return stateCode;
		return mResult;
	}
	/**
	 * @param url			请求url
	 * @param headers		请求header
	 * @param paramsMap		请求的表单参数
	 * @param filesMap		请求的文件参数
	 * @param paramsJson	请求的json参数
	 */
	public static int doGet(String url, HashMap<String, String> headers) {
				
		initDefaultHttpClient();
		
		if (!isRedirect) {
			preURL = url;
			preHeaders = headers;
		}
		isRedirect = false;
		
		HttpGet httpRequest = null;
		httpRequest = new HttpGet(url);
		// 设置header
		if (headers != null) {
			Iterator<Entry<String, String>> headersIterator = headers.entrySet().iterator();  
			while (headersIterator.hasNext()) {  
			    Entry<String, String> entry = headersIterator.next();
			    httpRequest.addHeader(entry.getKey(), entry.getValue());
			}
		}
		// 设置cookie
		try {
			httpRequest.addHeader("Cookie", FileOperation.readTxtFile(Constants.FILE_NAME).trim());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		 // 处理请求结果
		try {
			HttpResponse httpResponse = mHttpClient.execute(httpRequest);
			HttpEntity mHttpEntity = httpResponse.getEntity();
			stateCode = httpResponse.getStatusLine().getStatusCode();
            mResult = EntityUtils.toString(mHttpEntity, charset);
			if(stateCode == 401) {
				isRedirect = true;
//            	JsonObject jsonObject = new JsonObject();
//            	jsonObject.addProperty("UserName", Consts.GOLDFIRE_NAME);
//            	jsonObject.addProperty("Password", MD5Util.getCryptedPwd(Consts.GOLDFIRE_PWD));
            	
            	JSONObject jsonObject = new JSONObject();
            	jsonObject.put("UserName", Consts.GOLDFIRE_NAME);
            	jsonObject.put("Password", MD5Util.getCryptedPwd(Consts.GOLDFIRE_PWD));
            	doPost(Consts.LOGIN_URL, preHeaders, null, null, jsonObject);
            }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stateCode;
	}
	
	public static void main(String[] args) {
		doGet("http://www.baidu.com", null);
	}
}
