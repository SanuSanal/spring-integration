package com.ust.partyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.config.AggregatorGateway;
import com.ust.partyapplication.model.FamilyModel;

@Service
public class FamilyService {
	
	@Autowired
	private AggregatorGateway gateway;

	@ServiceActivator(inputChannel = "familyProcessSplitterChannel")
	public void processFamilyRequests(Message<FamilyModel> message) {
		String response = "FamilyService Processing family details. id: "+message.getPayload().getFamilyId()+", name: "+message.getPayload().getFamilyName();
		System.out.println(response);
		
		gateway.aggregateFamilies(message);
	}
}
