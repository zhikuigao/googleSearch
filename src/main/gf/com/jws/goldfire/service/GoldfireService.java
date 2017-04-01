package com.jws.goldfire.service;

import net.sf.json.JSONObject;

public interface GoldfireService {
	/*** 获取gf首页数据*/
	public  JSONObject  queryGfMainSearch(JSONObject json);
	/*** 获取右侧分类数据*/
	public JSONObject queryGfMainCategory(JSONObject json);
	/** * 获取右侧分类数据更多*/
	public JSONObject queryGfMainCategoryMore(JSONObject json);
	/** * 获取右侧分类数据详情*/
	public JSONObject queryGfMainCategoryDetail(JSONObject json);
	/*** 专利获取机构*/
	public JSONObject queryGfMainMechanisml(JSONObject json);
	/*** 非专利获取机构*/
	public JSONObject queryGfMainAritcleMechanisml(JSONObject json);
	/*** 非专利获取作者*/
	public JSONObject queryGfMainAritcleAuthor(JSONObject json);
	/*** 获取专利详情（URL的正文内容）*/
	public JSONObject queryGfMainPatent(JSONObject json);
	
	

}
