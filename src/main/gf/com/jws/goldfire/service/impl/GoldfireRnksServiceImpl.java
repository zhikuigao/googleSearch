package com.jws.goldfire.service.impl;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.jws.common.utils.JiveGlobe;
import com.jws.common.utils.ResultPackaging;
import com.jws.goldfire.constant.Constants;
import com.jws.goldfire.requestBean.InSearchData;
import com.jws.goldfire.requestBean.InSortArticleNumberData;
import com.jws.goldfire.service.GoldfireRnksService;
import com.jws.goldfire.util.HttpConnect;

public class GoldfireRnksServiceImpl implements GoldfireRnksService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Override
	public JSONObject queryGfMainSortArticleNumber(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page" , "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			String paramStr = new InSortArticleNumberData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language")).getQueryData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_Author , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error(e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}
	@Override
	public JSONObject queryGfMainSortArticleQuoteNumber(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page" , "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			String paramStr = new InSortArticleNumberData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language")).getQueryQuoteData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_Mechanisml , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error(e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}
	@Override
	public JSONObject queryGfMainSortArticleTitle(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page" , "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			String paramStr = new InSortArticleNumberData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language")).getQueryTilleData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_Mechanisml , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error(e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}
	@Override
	public JSONObject queryGfMainSortArticleMechanism(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page" , "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			String paramStr = new InSortArticleNumberData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language")).getQueryMechanismData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_MechanismrHot , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error(e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}

}
