package com.ust.partyapplication.model;

import java.util.List;

/**
 * @author admin
 *
 */
public class AuditModel {
	private String id;
	private String name;
	private List<FamilyModel> families;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public AuditModel(String id, String name, List<FamilyModel> families) {
		this.id = id;
		this.name = name;
		this.families = families;
	}
	@Override
	public String toString() {
		return "AuditModel [id=" + id + ", name=" + name + ", families=" + families + "]";
	}
	
}
