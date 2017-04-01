package com.jws.goldfire.requestBean;

public class InCategoryMoreDetailData extends InData {
	
	private int metaId;
	private int categoryId;
	public int getMetaId() {
		return metaId;
	}

	public void setMetaId(int metaId) {
		this.metaId = metaId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	private String answerId;
	public InCategoryMoreDetailData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}
	
	public String getQueryData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":"+Language+",\"ExpressionType\":4,\"Guid\":null,\"ParentTool\":0,\"KnowledgeBases\":[\"2|1\",\"4,4|1\",\"4,3|1\",\"4,5|1\"],\"KnowledgeCollections\":[],\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]},\"CState\":{\"Page\":[{\"Count\":"+Count+",\"From\":"+From+"}],\"ConceptId\":1,\"CategoryId\":"+categoryId+",\"AnswerId\":\""+answerId+"\"},\"FState\":{\"PageSize\":10},\"AutoExtendWithSynonyms\":true,\"LState\":{\"MetaId\":"+metaId+",\"ConceptId\":1},\"TextFacetFilters\":null,\"NumberFacetRangeFilters\":null,\"ContentClassesFilters\":null}";
	}

}
