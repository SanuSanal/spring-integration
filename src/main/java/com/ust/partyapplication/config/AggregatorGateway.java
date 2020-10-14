package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.ust.partyapplication.model.FamilyModel;

@MessagingGateway(name = "aggregator", defaultRequestChannel = "aggregatorSendChannel")
public interface AggregatorGateway {
	@Gateway
	public void aggregateFamilies(Message<FamilyModel> message);

}
