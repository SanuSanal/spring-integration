package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.ust.partyapplication.model.ReservationConfirmationModel;

@MessagingGateway(defaultRequestChannel = "transformerInputChannel")
public interface TransformerGateway {

	@Gateway
	public void transformMessage(Message<ReservationConfirmationModel> message);
}
