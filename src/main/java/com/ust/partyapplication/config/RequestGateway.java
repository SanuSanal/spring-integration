package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "requestGateway", defaultRequestChannel = "requestChannel")
public interface RequestGateway {

	@Gateway
	public void reservationRequest(Message<?> message);
}
