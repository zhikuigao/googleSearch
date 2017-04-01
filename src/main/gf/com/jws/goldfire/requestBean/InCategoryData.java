package com.jws.goldfire.requestBean;

public class InCategoryData extends InData {

	public InCategoryData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}
	
	public String getQueryData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":"+Language+",\"ExpressionType\":4,\"Guid\":null,\"ParentTool\":0,\"KnowledgeBases\":[\"4,4|1\",\"4,3|1\",\"4,5|1\",\"2|1\"],\"KnowledgeCollections\":[],\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]},\"FState\":{\"PageSize\":"+Count+"},\"AutoExtendWithSynonyms\":true,\"LState\":{\"MetaId\":"+From+",\"ConceptId\":1},\"TextFacetFilters\":null,\"NumberFacetRangeFilters\":null,\"ContentClassesFilters\":null}";
	}

}
