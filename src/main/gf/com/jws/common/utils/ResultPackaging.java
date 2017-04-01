package com.jws.common.utils;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jws.goldfire.constant.Constants;


/**
 * 封装公共的返回函数
 * @author lx
 *
 */
public class ResultPackaging {

	/**
	 * 封装失败的返回函数
	 * @param code 调用结果 0 失败 1成功5
	 * @param desc  失败原因
	 * @return
	 */
	public static final  JSONObject dealJsonObject(int resultCode, String code,  Object object){ 
		JSONObject returnObject= new JSONObject();
		JSONObject returnCode= new  JSONObject();
		returnCode.put("code", resultCode);
		//此处可以做国际化
		returnCode.put("desc", code);
		returnObject.put("resultDesc", returnCode);
		if (null != object) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String gsont = gson.toJson(object).toString();	
			returnObject.put("result", gsont);
		}
		return returnObject ;
	}
	
	public static final  JSONObject dealJsonObject2(boolean istrue){
		JSONObject returnObject= new JSONObject();
		JSONObject returnCode= new  JSONObject();
		returnCode.put("code", Constants.RESULT_CODE_SUCCESS);
		returnCode.put("desc", Constants.RESULT_SUCCESS);		
		returnCode.put("istrue",istrue);		
		returnObject.put("resultDesc", returnCode);
		return returnObject ;
	}
	/**
	 * 封装成功的返回函数
	 * @param object
	 * @return
	 */
	public static final  JSONObject dealSuccessJsonObject(Object  object){	
		JSONObject returnObject= new JSONObject();
		JSONObject returnCode= new  JSONObject();
		returnCode.put("code", Constants.RESULT_CODE_SUCCESS);
		returnCode.put("desc", Constants.RESULT_SUCCESS);		
		returnObject.put("resultDesc", returnCode);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String gsont = gson.toJson(object);
		returnObject.put("result", gsont);
		return returnObject;
	}
	
	/**
	 * 封装成功的返回函数
	 * @param String
	 * @return
	 */
	public static final  JSONObject dealSuccessStr(String  str){	
		JSONObject returnObject= new JSONObject();
		JSONObject returnCode= new  JSONObject();
		returnCode.put("code", Constants.RESULT_CODE_SUCCESS);
		returnCode.put("desc", Constants.RESULT_SUCCESS);		
		returnObject.put("resultDesc", returnCode);
		Gson gson = new Gson();
//		String gsont = gson.toJson(str);
		returnObject.put("result", str);
		return returnObject;
	}
	/**
	 * 封装成功的返回函数
	 * @param object
	 * @return
	 */
	public static final  JSONObject dealSuccessJsonObject1(Object  object1,Object  object2){	
		JSONObject returnObject= new JSONObject();
		JSONObject returnCode= new  JSONObject();
		returnCode.put("code", Constants.RESULT_CODE_SUCCESS);
		returnCode.put("desc", Constants.RESULT_SUCCESS);		
		returnObject.put("resultDesc", returnCode);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		String gsont = gson.toJson(object1);
		JSONObject user= new  JSONObject();
		user.put("id", object1);
//		user.putAll(JsonUtil.toHashMap(object2));	
		user.put("sessionId", object2);
		returnObject.put("result", user);
		return returnObject;
	}
	
	/**
	 * 封装返回函数
	 * @param resultCode 返回码
	 * @param code  错误描述映射码
	 * @param language  返回信息语种
	 * @param   object  返回数据
	 * @return
	 */
	public static final  JSONObject dealSuccessJsonObject(int resultCode, String code,  Object object){ 
		JSONObject returnObject= new JSONObject();
		JSONObject returnCode= new  JSONObject();
		returnCode.put("code", resultCode);
		returnCode.put("desc", code);
		returnObject.put("resultDesc", returnCode);
		if (null != object) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String gsont = gson.toJson(object).toString();	
			returnObject.put("result", gsont);
		}
		return returnObject ;
	}
    
	

}
