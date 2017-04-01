package com.jwis.google.model;

public class SearchDatas {
	 private String title;
	 private String url;
	 private String htmlTitle;
	 public String getHtmlTitle() {
		return htmlTitle;
	}
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	private String summary;

}
