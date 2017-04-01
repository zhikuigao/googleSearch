package com.jws.goldfire.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.jws.goldfire.constant.Constants;


/**
 * goldfire连接工具类
 * HttpClint
 */
public class HttpConnect {
	   // protected static final LogTools log = new LogTools();
	    private static final int HTTP_TIMEOUTTIME = 180000;// 联网超时时间
	    private static final int HTTP_TIMESOOUT = 180000;// 服务器超时时间设置
	    private String url;
	    private String sendMessage;
	    private String strResult = "";
	    private int mark=1;
	    private List<NameValuePair> nameValuePairs;
	    private DefaultHttpClient mDefaultHttpClient;
	    
	    private String lastUrl;
	    private String lastParam;
	    private int lastHttpType;
	    
	    private String loginForwardUrl;

	    public String getStrResult() {
	        return strResult;
	    }

	    public HttpConnect(String url, String sendMessage, int getType) {
	        this.url = url;
	        System.out.println(url);
	        this.sendMessage = sendMessage;
	        this.lastUrl = url;
	        this.lastParam = sendMessage;
	        this.lastHttpType = getType;
	        init(getType);
	       
	    }
	    
	    public HttpConnect(String url, List<NameValuePair> nameValuePairs,
	                       int getType, int mark) {
	        this.url = url;
	        this.nameValuePairs = nameValuePairs;
	        init(getType);
	    }

	    private void init(int getType) {
	        initDefaultHttpClient();
	        if (getType == Constants.HTTP_POST) {
	            postHttp();
//	        } else if (getType == Constants.HTTP_POST_GOTYE) {
//	        	//postHttp_Gotye();
//	        } else if (getType == Constants.HTTP_GET_GOTYE) {
//	        	getHttp_Gotye();
	        } else if (getType == Constants.HTTP_GET) {
	            getHttp();
	        } else if (getType == Constants.HTTP_PUT) {
	            postPut();
	        } else if (getType == Constants.HTTP_DELETE) {
	            postDelete();
	        }
	    }

	    public void initDefaultHttpClient() {
	        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	        HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, true);
	        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, HTTP_TIMEOUTTIME);// 连接超时设置
	        HttpConnectionParams.setSoTimeout(localBasicHttpParams, HTTP_TIMESOOUT);// 服务器超时设置
	        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 16384);
	        mDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	        mDefaultHttpClient.setRedirectHandler(new RedirectHandler() {
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

	    private void getHttp() {
	        HttpGet mHttpRequest = null;
	        mHttpRequest = new HttpGet(url);
	        mHttpRequest.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
	        mHttpRequest.addHeader("Referer",url);

            try {
            	mHttpRequest.addHeader("aft", FileOperation.readTxtFile(Constants.GF_AFT_FILE_NAME).trim());
				mHttpRequest.addHeader("Cookie",FileOperation.readTxtFile(Constants.FILE_NAME).trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
            
	        try {
	            HttpResponse mHttpResponse = mDefaultHttpClient.execute(mHttpRequest);
//	            EntityUtils.toString(new GzipDecompressingEntity(mHttpResponse.getEntity())); 
//	            mHttpResponse.setEntity(new GzipDecompressingEntity(mHttpResponse.getEntity()));
	            int stateCode = mHttpResponse.getStatusLine().getStatusCode();// 获得联网状态
	            if (stateCode == 200) {// 联网成功
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	                if(null != loginForwardUrl && url.equals(loginForwardUrl)){
	                	String aft = strResult.substring(strResult.indexOf("aft: '"), strResult.indexOf("titleResId"));
	                	aft = aft.replace("aft: '", "").replace("',", "");
	                	FileOperation.saveCookie(aft, Constants.GF_AFT_FILE_NAME);	
	                	url = lastUrl;
		            	sendMessage = lastParam;
		            	init(lastHttpType);
	                }
//	                strResult = EntityUtils.toString(new GzipDecompressingEntity(mHttpEntity), "utf-8");
	            }else if(stateCode == 404){
	                strResult=String.valueOf(stateCode);
	            }else if(stateCode == 401){
//	            	System.out.println("here is 401");
	            	StringBuffer sbBuffer = new StringBuffer();
	            	sbBuffer.append("UserName=" + Constants.GOLDFIRE_NAME);
	            	sbBuffer.append("&Password=" + MD5Util.getCryptedPwd(Constants.GOLDFIRE_PWD));
	            	url=Constants.LOGIN_URL;
	    	        sendMessage=sbBuffer.toString();
	    	        init(Constants.HTTP_POST);
	            } else {
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            }
	        } catch (ClientProtocolException e) {// 报出异常则回收资源
	            strResult = null;
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (ConnectTimeoutException cte) {
	            cte.printStackTrace();
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (SocketTimeoutException ste) {
	            ste.printStackTrace();
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (IOException e) {
	            mDefaultHttpClient.getConnectionManager().shutdown();
	            strResult = null;
	        } finally {
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        }
	    }

	    public void cancel() {
	        if (mDefaultHttpClient != null) {
	            try {
	                mDefaultHttpClient.getConnectionManager().shutdown();
	            } catch (Exception e) {
	                strResult = null;
	            }
	        }
	    }
	    
	    private void postHttp() {
	        HttpPost mHttpRequest = null;
	        mHttpRequest = new HttpPost(url);
	        mHttpRequest.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");

            try {
            	mHttpRequest.addHeader("aft", FileOperation.readTxtFile(Constants.GF_AFT_FILE_NAME).trim());
				mHttpRequest.addHeader("Cookie", FileOperation.readTxtFile(Constants.FILE_NAME).trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
            
	        if (sendMessage != null && !"".equals(sendMessage)) {// post 数据
	            try {
	                if (sendMessage.contains("=")) {
	                    mHttpRequest.addHeader("Content-Type","application/x-www-form-urlencoded");
	                    List<NameValuePair> list = new ArrayList<NameValuePair>();
	                    if (sendMessage.indexOf("&") < 0) {
	                        if (sendMessage.indexOf("=") >= 0) {
	                            String[] array = sendMessage.split("=");
	                            if (array.length == 1) {
	                                list.add(new BasicNameValuePair(array[0], ""));
	                            } else {
	                                list.add(new BasicNameValuePair(array[0],
	                                        array[1]));
	                            }
	                        }
	                    } else {
	                        String[] arry = sendMessage.split("&");
	                        int n = arry.length;
	                        for (int i = 0; i < n; i++) {
	                            String string = arry[i];
	                            if (string != null && string.indexOf("=") >= 0) {
	                                String[] array = string.split("=");
	                                if (array.length == 1) {
	                                    list.add(new BasicNameValuePair(array[0],
	                                            ""));
	                                } else {
	                                    list.add(new BasicNameValuePair(array[0],
	                                            array[1]));
	                                }
	                            }
	                        }
	                    }
	                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
	                    mHttpRequest.setEntity(entity);
	                } else {
	                    mHttpRequest.addHeader("Content-Type", "application/json");
	                    StringEntity se;
	                    se = new StringEntity(sendMessage, "utf-8");
	                    mHttpRequest.setEntity(se);
	                }

	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        } else if (this.nameValuePairs != null
	                && this.nameValuePairs.size() > 0) {
	            mHttpRequest.getParams().setParameter(
	                    CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	            MultipartEntity entity = new MultipartEntity();
	            mHttpRequest.addHeader("enctype", "multipart/form-data");
	            int n = nameValuePairs.size();
	            for (int index = 0; index < n; index++) {

	                String key = nameValuePairs.get(index).getName();
	                String value = nameValuePairs.get(index).getValue();
	                if (key.equalsIgnoreCase("files[]")) {
	                    File file = new File(value);
	                    FileBody mFileBody = new FileBody(file, "image/jpg");
	                    entity.addPart(key, mFileBody);
	                } else if (key.equalsIgnoreCase("file[file]")) {
	                    mHttpRequest.addHeader("Content-Type",
	                            "application/x-www-form-urlencoded");

	                } else {
	                    try {
	                        StringBody stringBody = new StringBody(value);
	                        entity.addPart(key, stringBody);
	                    } catch (UnsupportedEncodingException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	            mHttpRequest.setEntity(entity);
	        }

	        try {
	            HttpResponse mHttpResponse = mDefaultHttpClient.execute(mHttpRequest);
	            if(url.contains("login")){
	                StringBuilder cookieStr = new StringBuilder();
	                Header[] headers = mHttpResponse.getAllHeaders();
	                for (int i = 0; i < headers.length; i++) {
//	                    System.out.println(headers[i].getName() + ":" + headers[i].getValue());
	                    if(headers[i].getName().equals("Set-Cookie")){
	                        cookieStr.append(headers[i].getValue()).append(";");
	                    }
	                }
	                //保存cookie
	                FileOperation.saveCookie(cookieStr.toString(),Constants.FILE_NAME);
	            }

	            int stateCode = mHttpResponse.getStatusLine().getStatusCode();// 获得联网状态
	            System.out.println("<<<获取登录状态"+stateCode);
	            if (stateCode == 200) {// 联网成功
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                //得到返回数据
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            } else if(stateCode == 302){
	            	mHttpRequest.abort();
	            	
	            	String locationUrl = mHttpResponse.getLastHeader("Location").getValue();
	            	loginForwardUrl = Constants.REMOVE_URL_PRIFIX + locationUrl.replace("/gftab", "");
	            	
	            	url = loginForwardUrl;
	            	init(Constants.HTTP_GET);
	            }else if(stateCode == 404){
	                strResult=String.valueOf(stateCode);
	            }else if(stateCode == 401 || stateCode == 307){
	            	StringBuffer sbBuffer = new StringBuffer();
	            	sbBuffer.append("UserName=" + Constants.GOLDFIRE_NAME);
	            	sbBuffer.append("&Password=" + MD5Util.getCryptedPwd(Constants.GOLDFIRE_PWD));
	            	
	            	url=Constants.LOGIN_URL;
	    	        sendMessage=sbBuffer.toString();
	    	        
	    	        init(Constants.HTTP_POST);
	            } else{
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            }
	        } catch (ClientProtocolException e) {// 报出异常则回收资源
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (ConnectTimeoutException cte) {
	            cte.printStackTrace();
	            //JW_BaseActivity.handler.sendEmptyMessage(2);
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (SocketTimeoutException ste) {
	            ste.printStackTrace();
	            //JW_BaseActivity.handler.sendEmptyMessage(2);
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (IOException e) {
	            strResult = null;
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } finally {
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        }
	    }

	    private void postPut() {
	        HttpPut mHttpRequest = null;
	        mHttpRequest = new HttpPut(url);
	        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	        HttpConnectionParams
	                .setStaleCheckingEnabled(localBasicHttpParams, true);
	        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams,
	                HTTP_TIMEOUTTIME);// 连接超时设置
	        HttpConnectionParams.setSoTimeout(localBasicHttpParams, HTTP_TIMESOOUT);// 服务器超时设置
	        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 16384);
	        mDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	        mHttpRequest.addHeader("Host", Constants.REMOVE_URL_PRIFIX.replace("http://", "").replace("/", ""));

	        if (sendMessage != null && !"".equals(sendMessage)) {// post 数据
	            try {
	                if (sendMessage.contains("=")) {
	                    mHttpRequest.addHeader("Content-Type",
	                            "application/x-www-form-urlencoded");
	                    List<NameValuePair> list = new ArrayList<NameValuePair>();
	                    if (sendMessage.indexOf("&") < 0) {
	                        if (sendMessage.indexOf("=") >= 0) {
	                            String[] array = sendMessage.split("=");
	                            if (array.length == 1) {
	                                list.add(new BasicNameValuePair(array[0], ""));
	                            } else {
	                                list.add(new BasicNameValuePair(array[0],
	                                        array[1]));
	                            }
	                        }
	                    } else {
	                        String[] arry = sendMessage.split("&");
	                        int n = arry.length;
	                        for (int i = 0; i < n; i++) {
	                            String string = arry[i];
	                            if (string != null && string.indexOf("=") >= 0) {
	                                String[] array = string.split("=");
	                                if (array.length == 1) {
	                                    list.add(new BasicNameValuePair(array[0],
	                                            ""));
	                                } else {
	                                    list.add(new BasicNameValuePair(array[0],
	                                            array[1]));
	                                }
	                            }
	                        }
	                    }
	                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
	                            list, "UTF-8");
	                    mHttpRequest.setEntity(entity);
	                } else {
	                    mHttpRequest.addHeader("Content-Type", "application/json");
	                    StringEntity se;
	                    se = new StringEntity(sendMessage, "utf-8");
	                    mHttpRequest.setEntity(se);
	                }

	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        }

	        try {
	            HttpResponse mHttpResponse = mDefaultHttpClient
	                    .execute(mHttpRequest);
	            int stateCode = mHttpResponse.getStatusLine().getStatusCode();// 获得联网状态
	            if (stateCode == 200) {// 联网成功
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            } else {
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            }
	        } catch (ClientProtocolException e) {// 报出异常则回收资源
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (ConnectTimeoutException cte) {
	            cte.printStackTrace();
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (SocketTimeoutException ste) {
	            ste.printStackTrace();
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (IOException e) {
	            strResult = null;
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } finally {
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        }
	    }

	    private void postDelete() {
	        HttpDelete mHttpRequest = null;
	        mHttpRequest = new HttpDelete(url);
	        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	        HttpConnectionParams
	                .setStaleCheckingEnabled(localBasicHttpParams, true);
	        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams,
	                HTTP_TIMEOUTTIME);// 连接超时设置
	        HttpConnectionParams.setSoTimeout(localBasicHttpParams, HTTP_TIMESOOUT);// 服务器超时设置
	        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 16384);
	        mDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	        mHttpRequest.addHeader("Host", Constants.REMOVE_URL_PRIFIX.replace("http://", "").replace("/", ""));

	        try {
	            HttpResponse mHttpResponse = mDefaultHttpClient
	                    .execute(mHttpRequest);
	            int stateCode = mHttpResponse.getStatusLine().getStatusCode();// 获得联网状态
	            if (stateCode == 200) {// 联网成功
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            } else {
	                HttpEntity mHttpEntity = mHttpResponse.getEntity();
	                strResult = EntityUtils.toString(mHttpEntity, "utf-8");
	            }
	        } catch (ClientProtocolException e) {// 报出异常则回收资源
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (ConnectTimeoutException cte) {
	            cte.printStackTrace();
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (SocketTimeoutException ste) {
	            ste.printStackTrace();
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } catch (IOException e) {
	            strResult = null;
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        } finally {
	            mDefaultHttpClient.getConnectionManager().shutdown();
	        }
	    }

	}
