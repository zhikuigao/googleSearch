package com.jws.goldfire.constant;

import java.io.File;







/**
 * 系统常量
 * @author wujh	
 *
 */
public class Constants {
	private Constants() {
	}

	
	/**
	 * 接口返回结果
	 */
	public static final int RESULT_CODE_SUCCESS = 1;//成功
	public static final int RESULT_CODE_FAIL = 0;   //失败
	public static final int RESULT_CODE_ERROR = 2;   //失败
	
	/**
	 * 接口返回原因
	 */
	public static final String CODE_LACK_PARAMETER = "参数有误";   //失败
	public static final String RESULT_SUCCESS = "成功";   
	public static final String RESULT_ERROR = "程序异常";   
	/**
	 * http请求
	 */
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	public static final int HTTP_POST_GOTYE = 2;
	public static final int HTTP_PUT = 4;
	public static final int HTTP_DELETE = 5;
	
	
	/**
	 * 请求地址
	 */
	//读取常量配置表
		public static String  properties="D:\\cooks";
	//goldfire地址前缀
	public static String REMOVE_URL_PRIFIX = "https://gfh1.goldfire.com/gftab";
	public  final static String GF_AFT_FILE_NAME = properties + File.separator + "Goldfire_aft.txt";
	public  final static String FILE_NAME = properties + File.separator + "Goldfire_cookie.txt";
	public static  String GOLDFIRE_NAME = "wangnizhu@hotmail.com";
	public static  String GOLDFIRE_PWD = "TANGjun1";
	public static final String LOGIN_URL = REMOVE_URL_PRIFIX + "/ghc/account/login";
	//首页
	public static final String Gf_LIST_URL = REMOVE_URL_PRIFIX + "/api/Researcher?time=" + System.currentTimeMillis();
	//右侧分类
	public static final String Gf_LIST_Category = REMOVE_URL_PRIFIX + "/api/Lenses?time=" + System.currentTimeMillis();
	//右侧分类更多
	public static final String Gf_LIST_CategoryMore = REMOVE_URL_PRIFIX + "/api/Lens?time=" + System.currentTimeMillis();
	//详情
	public static final String Gf_LIST_Detail = REMOVE_URL_PRIFIX + "/api/Citations?time=" + System.currentTimeMillis();
	//机构
	public static final String Gf_LIST_Mechanisml = REMOVE_URL_PRIFIX + "/api/ita/Patents?time=" + System.currentTimeMillis();
	//作者数量
	public static final String Gf_LIST_Author= REMOVE_URL_PRIFIX + "/api/ita/Inventors?time=" + System.currentTimeMillis();
	//机构
	public static final String Gf_LIST_MechanismrHot= REMOVE_URL_PRIFIX + "/api/ita/Companies?time=" + System.currentTimeMillis();
	//非专利机构
	public static final String Gf_LIST_MechanismrArticle= REMOVE_URL_PRIFIX + "/api/Facets?time=" + System.currentTimeMillis();
	//专利详情url
	public static final String PATENTS_DETAIL_URL = REMOVE_URL_PRIFIX + "/ghc/patview/";
}
