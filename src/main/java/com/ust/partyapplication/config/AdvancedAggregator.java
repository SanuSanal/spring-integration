package com.ust.partyapplication.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.ust.partyapplication.model.FamilyModel;
import com.ust.partyapplication.model.ReservationRequestModel;

@Component
public class AdvancedAggregator {

	@CorrelationStrategy
	public Object getCorrelationId(Message<FamilyModel> family) {
		return family.getHeaders().get("REQ_ID");
	}
	
	@ReleaseStrategy
	public boolean releaseCheck(List<Message<FamilyModel>> messages) {
		if (messages.size() > 0) {
			int totalMsgs = (int) messages.get(0).getHeaders().get("TOTAL_MESSAGES");
			if (messages.size() >= totalMsgs) {
				return true;
			}
		}
		return false;
	}
	
	@Aggregator(inputChannel = "aggregatorSendChannel", outputChannel = "aggregatorRecieverChannel")
	public ReservationRequestModel aggregateFamilyData(List<Message<FamilyModel>> messages) {
		ReservationRequestModel requestModel = new ReservationRequestModel();
		requestModel.setRequestId((String)messages.get(0).getHeaders().get("REQ_ID"));
		requestModel.setFamilies(messages.stream().map(message -> message.getPayload()).collect(Collectors.toList()));
		return requestModel;
	}
}
