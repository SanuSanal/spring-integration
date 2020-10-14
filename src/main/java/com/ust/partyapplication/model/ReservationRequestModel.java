package com.ust.partyapplication.model;

import java.util.List;

public class ReservationRequestModel {
	private String requestId;
	private String name;
	private List<FamilyModel> families;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FamilyModel> getFamilies() {
		return families;
	}
	public void setFamilies(List<FamilyModel> families) {
		this.families = families;
	}
	
	
}
