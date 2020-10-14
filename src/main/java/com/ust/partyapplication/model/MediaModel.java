package com.ust.partyapplication.model;

public class MediaModel {

	private String image_url;
    private String image_url_thumb;
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getImage_url_thumb() {
		return image_url_thumb;
	}
	public void setImage_url_thumb(String image_url_thumb) {
		this.image_url_thumb = image_url_thumb;
	}
	@Override
	public String toString() {
		return "MediaModel [image_url=" + image_url + ", image_url_thumb=" + image_url_thumb + "]";
	}
    
    
}
