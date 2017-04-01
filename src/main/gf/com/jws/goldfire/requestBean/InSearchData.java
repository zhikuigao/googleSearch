package com.jws.goldfire.requestBean;

public class InSearchData extends InData{
	
	public InSearchData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}

	private String knowledgeBases = "[\"2|1\",\"4,4|1\",\"4,3|1\",\"4,5|1\"]";

	
	public String getKnowledgeBases() {
		return knowledgeBases;
	}

	public void setKnowledgeBases(String knowledgeBases) {
		this.knowledgeBases = knowledgeBases;
	}



	public String getQueryData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":"+Language+",\"ExpressionType\":4,\"KnowledgeBases\":"+knowledgeBases+",\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]},\"MostRelevantOnly\":false,\"CState\":{\"Page\":[{\"Count\":"+Count+",\"From\":"+From+"}]},\"FState\":{\"PageSize\":10},\"AutoExtendWithSynonyms\":true,\"RenewCache\":1,\"AutoRefineQuery\":2}";
	}
	
	public String getQueryNoPatentData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":2052,\"ExpressionType\":4,\"KnowledgeBases\":"+knowledgeBases+",\"TranslationSettings\":{\"TargetLanguage\":2052,\"SourceLanguages\":[1036,1031,1041,1033]},\"MostRelevantOnly\":false,\"MostRelevantOnly\":false,\"CState\":{\"Page\":[{\"Count\":"+Count+",\"From\":"+From+"}]},\"FState\":{\"PageSize\":10},\"AutoExtendWithSynonyms\":true,\"RenewCache\":1,\"AutoRefineQuery\":2}";
	}
}
