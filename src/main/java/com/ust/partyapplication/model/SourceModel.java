package com.ust.partyapplication.model;

public class SourceModel {
	private String name;
    private String html_url;
    private String api_url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getApi_url() {
		return api_url;
	}
	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}
	@Override
	public String toString() {
		return "SourceModel [name=" + name + ", html_url=" + html_url + ", api_url=" + api_url + "]";
	}

}
