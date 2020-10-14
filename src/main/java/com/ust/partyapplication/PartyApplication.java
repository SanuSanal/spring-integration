package com.ust.partyapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class PartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyApplication.class, args);
	}

}
