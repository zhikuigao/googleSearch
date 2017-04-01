package com.jws.goldfire.requestBean;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class InData {
	protected String Expression;
	protected int From;
	protected int Count;
	protected String Language;
	
	
	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public InData(String Expression, int From,int Count,String Language) {
		this.Expression = Expression;
		this.From = From;
		this.Count = Count;
		this.Language=Language;
	}
	
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public String getExpression() {
		return Expression;
	}

	public void setExpression(String expression) {
		Expression = expression;
	}

	public int getFrom() {
		return From;
	}

	public void setFrom(int from) {
		From = from;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

}
