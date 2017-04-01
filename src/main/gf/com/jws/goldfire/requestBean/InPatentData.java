package com.jws.goldfire.requestBean;

public class InPatentData extends InData{

	
	public InPatentData(String Expression, int From, int Count,String Language) {
		super(Expression, From, Count,Language);
		// TODO Auto-generated constructor stub
	}

	public String getQueryData(){
		return "{\"Expression\":\""+Expression+"\",\"Language\":2052,\"ExpressionType\":4,\"KnowledgeBases\":[\"2|1\",\"4,4|1\",\"4,3|1\",\"4,5|38191\",\"4,5|38190\",\"4,5|48712\",\"4,5|44873\",\"4,5|20027\",\"4,5|27707\",\"4,5|140479\",\"4,5|140479\"],\"TranslationSettings\":{\"TargetLanguage\":2052,\"SourceLanguages\":[1036,1031,1041,1033]},\"MostRelevantOnly\":false,\"CState\":{\"Page\":[{\"Count\":"+Count+",\"From\":"+From+"}]},\"FState\":{\"PageSize\":10},\"AutoExtendWithSynonyms\":true,\"RenewCache\":1,\"AutoRefineQuery\":2}";
	}
}
