package com.jws.goldfire.requestBean;

public class InAuthorlData  extends InData{


	public InAuthorlData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}

	public String getQueryData(){
		return "{\"Query\":{\"Expression\":\""+Expression+" <in> FTXT\",\"Language\":2052,\"Scope\":{\"Items\":[{\"Type\":1,\"Id\":\"4,3|1\"}]},\"ExpressionType\":0,\"TranslationSettings\":{\"TargetLanguage\":2052,\"SourceLanguages\":[1036,1031,1041]}},\"TrendType\":\"INV\",\"SearchType\":1,\"PageFilter\":{\"From\":0,\"Count\":20},\"SortField\":\"CPC\",\"Ascending\":false,\"Assignees\":[],\"ActivityFilter\":0}";
	}
}
