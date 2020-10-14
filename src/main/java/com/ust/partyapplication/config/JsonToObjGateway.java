package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(defaultRequestChannel = "jsonToObjInput")
public interface JsonToObjGateway {

	@Gateway
	public void parseMsg(Message<?> message);
}
