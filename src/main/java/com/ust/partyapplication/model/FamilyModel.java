package com.ust.partyapplication.model;

public class FamilyModel {
	
	private String familyId;
	private String familyName;
	
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	@Override
	public String toString() {
		return "FamilyModel [familyId=" + familyId + ", familyName=" + familyName + "]";
	}
}
