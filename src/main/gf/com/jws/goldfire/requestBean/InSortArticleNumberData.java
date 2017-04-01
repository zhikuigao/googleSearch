package com.jws.goldfire.requestBean;

public class InSortArticleNumberData extends InData {
	public InSortArticleNumberData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}

	
	
	public String getQueryData(){
		return "{\"Query\":{\"Expression\":\""+Expression+" <in> FTXT\",\"Language\":"+Language+",\"Scope\":{\"Items\":[{\"Type\":1,\"Id\":\"4,3|1\"}]},\"ExpressionType\":0,\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]}},\"TrendType\":\"INV\",\"SearchType\":1,\"PageFilter\":{\"From\":"+From+",\"Count\":"+Count+"},\"SortField\":\"CPC\",\"Ascending\":false,\"Assignees\":[],\"ActivityFilter\":0}";
	}
	
	public String getQueryQuoteData(){
		return "{\"Query\":{\"Expression\":\""+Expression+" <in> FTXT\",\"Language\":"+Language+",\"Scope\":{\"Items\":[{\"Type\":1,\"Id\":\"4,3|1\"}]},\"ExpressionType\":0,\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]}},\"TrendType\":\"PAT\",\"SearchType\":1,\"PageFilter\":{\"From\":"+From+",\"Count\":"+Count+"},\"SortField\":\"BCWREF\",\"Ascending\":false,\"Assignees\":[],\"ActivityFilter\":0,\"Columns\":[\"PN\",\"TTL\",\"BCWREF\",\"IN\",\"ISD\"]}";
	}
	
	public String getQueryTilleData(){
		return "{\"Query\":{\"Expression\":\""+Expression+" <in> FTXT\",\"Language\":"+Language+",\"Scope\":{\"Items\":[{\"Type\":1,\"Id\":\"4,3|1\"}]},\"ExpressionType\":0,\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]}},\"TrendType\":\"PAT\",\"SearchType\":1,\"PageFilter\":{\"From\":"+From+",\"Count\":"+Count+"},\"SortField\":\"TTL\",\"Ascending\":false,\"Assignees\":[],\"ActivityFilter\":0,\"Columns\":[\"PN\",\"TTL\",\"BCWREF\",\"IN\",\"ISD\"]}";
	}
	
	public String getQueryMechanismData(){
		return "{\"Query\":{\"Expression\":\""+Expression+" <in> FTXT\",\"Language\":"+Language+",\"Scope\":{\"Items\":[{\"Type\":1,\"Id\":\"4,3|1\"}]},\"ExpressionType\":0,\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]}},\"TrendType\":\"ASSIG\",\"SearchType\":1,\"PageFilter\":{\"From\":"+From+",\"Count\":"+Count+"},\"SortField\":\"ACR\",\"Ascending\":true,\"Assignees\":[],\"ActivityFilter\":0}";
	}
}
