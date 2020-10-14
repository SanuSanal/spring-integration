package com.ust.partyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.config.HeaderFilterGateway;
import com.ust.partyapplication.model.ReservationConfirmationModel;

@Service
public class ReservationConfirmationService {
	
	@Autowired
	private HeaderFilterGateway gateway;

	@ServiceActivator(inputChannel = "reservationConfirmationChannel")
	public void requestReservation(Message<ReservationConfirmationModel> message){
		String response = "Party reservation completed for id "+message.getPayload().getConfirmationId();
		System.out.println(response);
		String headerAuthToken = (String) message.getHeaders().get("AUTH_TOKEN");
		System.out.println("Auth token in header: "+headerAuthToken);
		
		gateway.filterHeader(message);
	}
}
