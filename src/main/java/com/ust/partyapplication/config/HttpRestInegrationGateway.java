package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(defaultRequestChannel = "httpOutRequest")
public interface HttpRestInegrationGateway {
	
	@Gateway
	public Message<?> getMessageFromExternalService(Message<?> message);
}
