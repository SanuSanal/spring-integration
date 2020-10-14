package com.ust.partyapplication.model;

import java.math.BigInteger;

public class IncidentClass {

	private int id;
    private String title;
    private String description;
    private  String address;
    private BigInteger occurred_at;
    private BigInteger updated_at;
    private String url;
    private SourceModel source;
    private MediaModel media;
    private String location_type;
    private String location_description;
    private String type;
    private String type_properties;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigInteger getOccurred_at() {
		return occurred_at;
	}
	public void setOccurred_at(BigInteger occurred_at) {
		this.occurred_at = occurred_at;
	}
	public BigInteger getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(BigInteger updated_at) {
		this.updated_at = updated_at;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public SourceModel getSource() {
		return source;
	}
	public void setSource(SourceModel source) {
		this.source = source;
	}
	public MediaModel getMedia() {
		return media;
	}
	public void setMedia(MediaModel media) {
		this.media = media;
	}
	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public String getLocation_description() {
		return location_description;
	}
	public void setLocation_description(String location_description) {
		this.location_description = location_description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_properties() {
		return type_properties;
	}
	public void setType_properties(String type_properties) {
		this.type_properties = type_properties;
	}
	@Override
	public String toString() {
		return "IncidentClass [id=" + id + ", title=" + title + ", description=" + description + ", address=" + address
				+ ", occurred_at=" + occurred_at + ", updated_at=" + updated_at + ", url=" + url + ", source=" + source
				+ ", media=" + media + ", location_type=" + location_type + ", location_description="
				+ location_description + ", type=" + type + ", type_properties=" + type_properties + "]";
	}
	
}
