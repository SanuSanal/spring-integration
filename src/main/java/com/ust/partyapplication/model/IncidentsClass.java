package com.ust.partyapplication.model;

import java.util.List;

public class IncidentsClass {
	private List<IncidentClass> incidents;

	public List<IncidentClass> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<IncidentClass> incidents) {
		this.incidents = incidents;
	}

	@Override
	public String toString() {
		return "IncidentsClass [incidents=" + incidents + "]";
	}
	
}
