package com.ust.partyapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.ust.partyapplication.model.ReservationConfirmationModel;

@MessagingGateway(name = "headerFilterGateway", defaultRequestChannel = "headerFilterInputChannel")
public interface HeaderFilterGateway {

	@Gateway
	public void filterHeader(Message<ReservationConfirmationModel> message);
}
