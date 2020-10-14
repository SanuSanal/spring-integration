package com.ust.partyapplication.model;

import java.util.List;

public class ReservationConfirmationModel {
	private String confirmationId;
	private String name;
	private List<FamilyModel> families;
	public String getConfirmationId() {
		return confirmationId;
	}
	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
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
