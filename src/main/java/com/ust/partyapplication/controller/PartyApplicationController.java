package com.ust.partyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.partyapplication.config.RequestGateway;
import com.ust.partyapplication.model.ReservationConfirmationModel;
import com.ust.partyapplication.model.ReservationRequestModel;

@RestController
@RequestMapping("/api/v1")
public class PartyApplicationController {

	@Autowired
	private RequestGateway gateway;
	
	@PostMapping("/partyrequest")
	public String partyRequest(@RequestBody ReservationRequestModel requestModel) {
		gateway.reservationRequest(MessageBuilder.withPayload(requestModel).build());
		return "Reservation Request placed!!";
	}
	
	@PostMapping("/partyconfirmation")
	public String partyRequest(@RequestBody ReservationConfirmationModel confirmationModel) {
		gateway.reservationRequest(MessageBuilder.withPayload(confirmationModel).build());
		return "Reservation confirmation placed!!";
	}
	
}
