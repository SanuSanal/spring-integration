package com.ust.partyapplication.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.model.ReservationRequestModel;

@Service
public class AggregatorService {

	@ServiceActivator(inputChannel = "aggregatorRecieverChannel")
	public void aggregateFamilies(Message<ReservationRequestModel> requestModel) {
		ReservationRequestModel model = requestModel.getPayload();
		System.out.println("AggregatorService processing recieved data. id: " +model.getRequestId());
		model.getFamilies().stream().forEach(System.out::println);
	}
}
