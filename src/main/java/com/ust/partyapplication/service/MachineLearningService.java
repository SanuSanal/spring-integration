package com.ust.partyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.config.TransformerGateway;
import com.ust.partyapplication.model.ReservationConfirmationModel;

@Service
public class MachineLearningService {
	
	@Autowired
	private TransformerGateway gateway;

	@ServiceActivator(inputChannel = "headerFilterOutputChannel")
	public void processAudits(Message<ReservationConfirmationModel> message) {
		System.out.println("MachineLearningService, Headers after filtering: "+message.getHeaders());
		gateway.transformMessage(message);
	}
}
