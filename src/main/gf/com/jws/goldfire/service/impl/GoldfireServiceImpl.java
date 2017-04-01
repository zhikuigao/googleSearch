package com.jws.goldfire.service.impl;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.jws.common.utils.JiveGlobe;
import com.jws.common.utils.ResultPackaging;
import com.jws.goldfire.constant.Constants;
import com.jws.goldfire.requestBean.InCategoryData;
import com.jws.goldfire.requestBean.InCategoryMoreData;
import com.jws.goldfire.requestBean.InCategoryMoreDetailData;
import com.jws.goldfire.requestBean.InMechanismlData;
import com.jws.goldfire.requestBean.InPatentData;
import com.jws.goldfire.requestBean.InSearchData;
import com.jws.goldfire.service.GoldfireService;
import com.jws.goldfire.util.HttpConnect;

public class GoldfireServiceImpl implements GoldfireService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Override
	public JSONObject queryGfMainSearch(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty("queryTxt", "page","knowledgeBases" , "pageSize","language",json)){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			InSearchData searchData = new InSearchData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language"));
			String knowledgeBases = json.optString("knowledgeBases");
			if(!JiveGlobe.isEmpty(knowledgeBases)){
				knowledgeBases = knowledgeBases.equals("0")?"[\"2|1\",\"4,4|1\",\"4,5|1\"]":"[\"4,3|1\"]";
				searchData.setKnowledgeBases(knowledgeBases);
			}
			String paramStr = searchData.getQueryData();
			System.out.println(paramStr);
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_URL , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error("<<<获取首页数据异常"+e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}

	@Override
	public JSONObject queryGfMainCategory(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "metaId" , "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			String paramStr = new InCategoryData(json.optString("queryTxt"), json.optInt("metaId"), json.optInt("pageSize"),json.optString("language")).getQueryData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_Category , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error("<<<获取右侧结构数据异常"+e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}

	@Override
	public JSONObject queryGfMainCategoryMore(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty("page", "pageSize", "queryTxt", "metaId", "categoryId", "language",json)){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			InCategoryMoreData record = new InCategoryMoreData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language"));
			record.setMetaId(json.optInt("metaId"));
			record.setCategoryId(json.optInt("categoryId"));
			String paramStr = record.getQueryData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_CategoryMore , paramStr, Constants.HTTP_POST);
			String result = connect.getStrResult();
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error("<<<获取右侧数据更多异常"+e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}

	@Override
	public JSONObject queryGfMainCategoryDetail(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty("page", "pageSize", "queryTxt", "metaId", "categoryId","answerId","language", json)){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			InCategoryMoreDetailData record = new InCategoryMoreDetailData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language"));
			record.setMetaId(json.optInt("metaId"));
			record.setCategoryId(json.optInt("categoryId"));
			record.setAnswerId(json.optString("answerId"));
			String paramStr = record.getQueryData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_Detail , paramStr, Constants.HTTP_POST);
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
	public JSONObject queryGfMainMechanisml(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page", "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			InMechanismlData record = new InMechanismlData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language"));
			String paramStr = record.getQueryData();
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
	public JSONObject queryGfMainAritcleMechanisml(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page", "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			InMechanismlData record = new InMechanismlData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language"));
			String paramStr = record.getQueryArtcileData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_MechanismrArticle , paramStr, Constants.HTTP_POST);
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
	public JSONObject queryGfMainAritcleAuthor(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(JiveGlobe.isEmpty(json,"queryTxt", "page", "pageSize","language")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			InMechanismlData record = new InMechanismlData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"),json.optString("language"));
			String paramStr = record.getQueryAuthorData();
			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_MechanismrArticle , paramStr, Constants.HTTP_POST);
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
	public JSONObject queryGfMainPatent(JSONObject json) {
		JSONObject returnObject= new JSONObject();
		if(json==null || !json.has("patentId")){
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
			return returnObject;
		}
		try {
			String paramStr = json.optString("patentId");
			HttpConnect connect = new HttpConnect(Constants.PATENTS_DETAIL_URL + paramStr, "", Constants.HTTP_POST);
			String result = splitResult(connect.getStrResult());
			System.out.println(result);
			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
		} catch (Exception e) {
			logger.error(e);
			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
		}
		return returnObject;
	}

//	@Override
//	public JSONObject queryGfMainAuthorNumber(JSONObject json) {
//		JSONObject returnObject= new JSONObject();
//		if(JiveGlobe.isEmpty("queryTxt", "page", json, "pageSize")){
//			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_FAIL, Constants.CODE_LACK_PARAMETER, null);
//			return returnObject;
//		}
//		try {
//			InMechanismlData record = new InMechanismlData(json.optString("queryTxt"), json.optInt("page"), json.optInt("pageSize"));
//			String paramStr = record.getQueryData();
//			HttpConnect connect = new HttpConnect(Constants.Gf_LIST_Author , paramStr, Constants.HTTP_POST);
//			String result = connect.getStrResult();
//			System.out.println(result);
//			returnObject = ResultPackaging.dealJsonObject(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_SUCCESS,result);
//		} catch (Exception e) {
//			logger.error(e);
//			return  ResultPackaging.dealJsonObject(Constants.RESULT_CODE_ERROR, Constants.RESULT_ERROR,null);
//		}
//		return returnObject;
//	}

	/**
	 * 对专利详情返回串做截取操作，前端展示只需要其中的一个json串
	 * @param result
	 * @return
	 */
	private String splitResult(String result){
		String startStr = "imcon.httpParams.patentData = '";
		String endStr = "imcon.httpParams.patentRefUrlTemplate";
        int start = result.indexOf(startStr) + startStr.length();
        int end = result.indexOf(endStr, start) - 2;
        result = result.substring(start, end);
        int newEnd = result.lastIndexOf("'");
        result = result.substring(0, newEnd);
        result = result.replace("\\\"", "\"");
        if (null != result) {
//            Type type = new TypeToken<ArrayList<RowEntity>>(){}.getType();
//            ParserNewFacade parser = new ParserNewFacade(requestCode, PatentDetailItemActivity.this);
//            parser.start(result, "Rows", type);
        }
        return result;
    }

}
