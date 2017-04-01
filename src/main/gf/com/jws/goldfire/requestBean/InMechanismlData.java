package com.jws.goldfire.requestBean;

public class InMechanismlData  extends InData{


	public InMechanismlData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}

	public String getQueryData(){
		return "{\"Query\":{\"Expression\":\""+Expression+" <in> FTXT\",\"Language\":"+Language+",\"Scope\":{\"Items\":[{\"Type\":1,\"Id\":\"4,3|1\"}]},\"ExpressionType\":0,\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]}},\"TrendType\":\"PAT\",\"SearchType\":1,\"PageFilter\":{\"From\":"+From+",\"Count\":"+Count+"},\"SortField\":\"ISD\",\"Ascending\":false,\"Assignees\":[],\"ActivityFilter\":0,\"Columns\":[\"PN\",\"ISD\",\"TTL\",\"AN\"]}";
	}
	
	public String getQueryArtcileData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":"+Language+",\"ExpressionType\":4,\"Guid\":null,\"ParentTool\":0,\"KnowledgeBases\":[\"2|1\",\"4,4|1\",\"4,5|1\"],\"KnowledgeCollections\":[],\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]},\"CState\":{\"Page\":[{\"Count\":\""+Count+"\",\"From\":"+From+"}]},\"FState\":{\"Items\":[{\"Query\":\"\",\"FacetId\":\"F_PBL\",\"Page\":{\"Count\":40,\"From\":0}}]},\"AutoExtendWithSynonyms\":true,\"ContentClassesFilters\":[]}";
	}
	
	public String getQueryAuthorData(){
		return "{\"Expression\":\"飞秒激光\",\"Language\":"+Language+",\"ExpressionType\":4,\"Guid\":null,\"ParentTool\":0,\"KnowledgeBases\":[\"2|1\",\"4,4|1\",\"4,5|1\"],\"KnowledgeCollections\":[],\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]},\"MostRelevantOnly\":false,\"CState\":{\"Page\":[{\"Count\":\"100\",\"From\":0}]},\"FState\":{\"Items\":[{\"Query\":\"\",\"FacetId\":\"F_ATHR\",\"Page\":{\"Count\":40,\"From\":0}}]},\"AutoExtendWithSynonyms\":true,\"ContentClassesFilters\":[]}";
	}
}
