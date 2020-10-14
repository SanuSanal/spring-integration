package com.ust.partyapplication.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.ust.partyapplication.model.AuditModel;

@Service
public class AuditService {

	@ServiceActivator(inputChannel = "transormerOutputChannel")
	public void auditReservationConfirmations(Message<AuditModel> message) {
		System.out.println((AuditModel)message.getPayload());
	}
}
