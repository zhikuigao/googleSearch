package com.jws.goldfire.service;

import net.sf.json.JSONObject;

public interface GoldfireRnksService {
	/*** 根据文章数量排序*/
	public  JSONObject  queryGfMainSortArticleNumber(JSONObject json);
	/*** 根据文章引用数量排序*/
	public  JSONObject  queryGfMainSortArticleQuoteNumber(JSONObject json);
	/*** 根据文章标题相关性*/
	public  JSONObject  queryGfMainSortArticleTitle(JSONObject json);
	/*** 根据文章机构相关性(活跃度)*/
	public  JSONObject  queryGfMainSortArticleMechanism(JSONObject json);
}
