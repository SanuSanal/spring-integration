package com.ust.partyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.config.HttpRestInegrationGateway;
import com.ust.partyapplication.config.JsonToObjGateway;
import com.ust.partyapplication.model.IncidentsClass;

@Service
class InboundMsgService {
	
	@Autowired
	private HttpRestInegrationGateway extGateway;
	
	@Autowired
	private JsonToObjGateway transformer;
	
	@ServiceActivator(inputChannel = "httpRequest")
	public String sendMessage(Message<?> message) {
		Message<?> respoMessage = extGateway.getMessageFromExternalService(MessageBuilder.withPayload("").build());
		System.out.println(respoMessage.getPayload());
		
		transformer.parseMsg(respoMessage);
		
		return (String) respoMessage.getPayload();
	}
	
	@ServiceActivator(inputChannel = "jsonToObjOutput")
	public void parsedMsg(Message<IncidentsClass> message) {
		System.out.println(message.getPayload());
	}

}
