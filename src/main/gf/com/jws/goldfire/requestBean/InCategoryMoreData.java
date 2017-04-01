package com.jws.goldfire.requestBean;

public class InCategoryMoreData extends InData {

	public InCategoryMoreData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}
	private int metaId;
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
	private int categoryId;
	
	public String getQueryData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":"+Language+",\"ExpressionType\":4,\"Guid\":null,\"ParentTool\":0,\"KnowledgeBases\":[\"4,4|1\",\"4,3|1\",\"4,5|1\",\"2|1\"],\"KnowledgeCollections\":[],\"TranslationSettings\":{\"TargetLanguage\":"+Language+",\"SourceLanguages\":[1036,1031,1041]},\"MostRelevantOnly\":false,\"AutoExtendWithSynonyms\":true,\"LState\":{\"MetaId\":"+metaId+",\"ConceptId\":1},\"TextFacetFilters\":null,\"NumberFacetRangeFilters\":null,\"ContentClassesFilters\":null,\"CategoryId\":"+categoryId+",\"ConceptId\":1,\"Page\":{\"From\":"+From+",\"Count\":"+Count+"}}";
	}

}
