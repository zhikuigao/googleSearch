package com.jws.goldfire.junit;

import net.sf.json.JSONObject;

import com.jws.goldfire.service.GoldfireRnksService;
import com.jws.goldfire.service.GoldfireService;
import com.jws.goldfire.service.impl.GoldfireRnksServiceImpl;
import com.jws.goldfire.service.impl.GoldfireServiceImpl;

public class MAIN {
		GoldfireService  gf = new GoldfireServiceImpl();
		GoldfireRnksService gfRnks = new GoldfireRnksServiceImpl();
		public static void main(String[] args) {
			MAIN in = new MAIN();
			//in.queryGfMainSearch();
		//	in.queryGfMainCategory();
		//	in.queryGfMainCategoryMore();
		//n.queryGfMainCategoryDetail();
		//in.queryGfMainMechanisml();
		//	in.queryGfMainSortArticleNumber();
		//	in.queryGfMainSortArticleQuoteNumber();
		//	in.queryGfMainSortArticleTitle();
		//	in.queryGfMainSortArticleMechanism();
		//	in.queryGfMainAritcleMechanisml();
		//	in.queryGfMainAritcleAuthor();
			in.queryGfMainPatent();
		}
		
		public JSONObject queryGfMainSearch(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"java\",\"page\":1,\"pageSize\":10,\"knowledgeBases\":\"\",\"language\":\"2052\"}");
			return gf.queryGfMainSearch(json);
		}
		public JSONObject queryGfMainCategory(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"java\",\"metaId\":0,\"pageSize\":10,\"language\":\"1033\"}");
			return gf.queryGfMainCategory(json);
		}
		public JSONObject queryGfMainCategoryMore(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"java\",\"metaId\":0,\"pageSize\":10,\"categoryId\":8,\"page\":1,\"language\":\"1033\"}}");
			return gf.queryGfMainCategoryMore(json);
		}
		public JSONObject queryGfMainCategoryDetail(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"java\",\"metaId\":0,\"pageSize\":10,\"categoryId\":8,\"page\":1,\"answerId\":\"3233868207|1041\",\"language\":\"1033\"}");
			return gf.queryGfMainCategoryDetail(json);
		}
		public JSONObject queryGfMainMechanisml(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"java\",\"page\":0,\"pageSize\":10,\"language\":\"1033\"}");
			return gf.queryGfMainMechanisml(json);
		}
		
		public JSONObject queryGfMainSortArticleNumber(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"飞秒激光\",\"page\":0,\"pageSize\":10}");
			return gfRnks.queryGfMainSortArticleNumber(json);
		}
		public JSONObject queryGfMainSortArticleQuoteNumber(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"飞秒激光\",\"page\":0,\"pageSize\":10}");
			return gfRnks.queryGfMainSortArticleQuoteNumber(json);
		}
		public JSONObject queryGfMainSortArticleTitle(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"飞秒激光\",\"page\":0,\"pageSize\":10}");
			return gfRnks.queryGfMainSortArticleTitle(json);
		}
		public JSONObject queryGfMainSortArticleMechanism(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"飞秒激光\",\"page\":0,\"pageSize\":10}");
			return gfRnks.queryGfMainSortArticleMechanism(json);
		}
		
		public JSONObject queryGfMainAritcleMechanisml(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"飞秒激光\",\"page\":0,\"pageSize\":10}");
			return gf.queryGfMainAritcleMechanisml(json);
		}
		
		public JSONObject queryGfMainAritcleAuthor(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"queryTxt\":\"飞秒激光\",\"page\":0,\"pageSize\":10}");
			return gf.queryGfMainAritcleAuthor(json);
		}
		
		public JSONObject queryGfMainPatent(){
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject("{\"patentId\":\"1696505697\",\"page\":0,\"pageSize\":10}");
			return gf.queryGfMainPatent(json);
		}
		
		
}
