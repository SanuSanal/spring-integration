package com.ust.partyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.config.FamilyDataGateway;
import com.ust.partyapplication.model.ReservationRequestModel;

@Service
public class ReservationRequestService {

	@Autowired
	private FamilyDataGateway gateway;
	
	@ServiceActivator(inputChannel = "reservationReqFilteredChannel")
	public void requestReservation(Message<ReservationRequestModel> message){
		String response = "Party reservation request raised. id: " + message.getPayload().getRequestId();
		System.out.println(response);
		
		gateway.sendDataForProcessFamily(message);
	}
}
