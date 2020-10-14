package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.ust.partyapplication.model.ReservationRequestModel;

@MessagingGateway(name ="familyDataGateway", defaultRequestChannel = "familyProcessChannel")
public interface FamilyDataGateway {

	@Gateway
	public void sendDataForProcessFamily(Message<ReservationRequestModel> message);
}
