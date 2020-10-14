package com.ust.partyapplication.config;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.HeaderFilter;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.ust.partyapplication.model.AuditModel;
import com.ust.partyapplication.model.FamilyModel;
import com.ust.partyapplication.model.IncidentsClass;
import com.ust.partyapplication.model.ReservationConfirmationModel;
import com.ust.partyapplication.model.ReservationRequestModel;

@Configuration
public class PartyAppliactionIntegrationConfig {

	//	MAIN CHANNEL
	@Bean
	public MessageChannel requestChannel() {
		return new DirectChannel();
	}

	//	MESSAGE ROUTED TO RESERVATION REQUEST CHANNEL BY ROUTER
	@Bean
	public MessageChannel reservationRequestChannel() {
		return new DirectChannel();
	}

	//	MESSAGE ROUTED TO RESERVATION CONFIRMATION CHANNEL BY ROUTER
	@Bean
	public MessageChannel reservationConfirmationChannel() {
		return new DirectChannel();
	}

	//	ROUTING BASED ON PAYLOAD TYPE
	@Bean
	@ServiceActivator(inputChannel = "requestChannel")
	public PayloadTypeRouter payloadTypeRouter() {
		PayloadTypeRouter payloadTypeRouter = new PayloadTypeRouter();
		payloadTypeRouter.setChannelMapping(ReservationRequestModel.class.getName(), "reservationRequestChannel");
		payloadTypeRouter.setChannelMapping(ReservationConfirmationModel.class.getName(), "headerEnricherChannel");
		return payloadTypeRouter;
	}

	//	FILTER OUT RESERVATION REQUESTS WITH INVALID ID/ ie, ID WITH LENGTH LESS THAN 3
	@Bean
	@Filter(inputChannel = "reservationRequestChannel", outputChannel = "reservationReqFilteredChannel")
	public MessageSelector invalidIdLengthFilter() {
		return message -> ((ReservationRequestModel)message.getPayload()).getRequestId().length() > 2;
	}

	//	MESSAGE CHANNEL FOR FILTERED RESERVATION REQUEST MESSAGES
	@Bean
	public MessageChannel reservationReqFilteredChannel() {
		return new DirectChannel();
	}

	//	CHANNEL TO SEND DATA TO SPLITTER
	@Bean
	public MessageChannel familyProcessChannel() {
		return new DirectChannel();
	}

	//	SPLITTING FAMILY OBJECTS AND SENDING TO ANOTHER CHANNEL
	@Splitter(inputChannel = "familyProcessChannel", outputChannel = "familyProcessSplitterChannel")
	public List<Message<FamilyModel>> familySplitter(Message<ReservationRequestModel> message){
		ReservationRequestModel requestModel = message.getPayload();
		return requestModel.getFamilies().stream().map(
				family -> MessageBuilder.withPayload(family)
				.setHeader("REQ_ID", requestModel.getRequestId())
				.setHeader("TOTAL_MESSAGES", requestModel.getFamilies().size())
				.build()).collect(Collectors.toList());
	}

	//	SPLITTER SECOND METHOD

	//		@Splitter(inputChannel = "familyProcessChannel", outputChannel = "familyProcessSplitterChannel")
	//		public List<FamilyModel> familySplitter(ReservationRequestModel model) {
	//			return model.getFamilies();
	//		}

	//	CHANNEL TO SEND SPLITTED DATA
	@Bean
	public MessageChannel familyProcessSplitterChannel() {
		return new DirectChannel();
	}

	//	CHANNEL TO SEND DATA TO AGGREGATOR
	@Bean
	public MessageChannel aggregatorSendChannel() {
		return new DirectChannel();
	}

	//	CHANNEL IN WHICH THE DATA WILL BE AVAILABLE AFTER COMBINING
	@Bean
	public MessageChannel aggregatorRecieverChannel() {
		return new DirectChannel();
	}

	//		AGGREGATING FUNCTION -- basic
	//		@Aggregator(inputChannel = "aggregatorSendChannel", outputChannel = "aggregatorRecieverChannel")
	//		public ReservationRequestModel aggregateFamilyData(List<Message<FamilyModel>> messages) {
	//			ReservationRequestModel requestModel = new ReservationRequestModel();
	//			requestModel.setRequestId((String)messages.get(0).getHeaders().get("REQ_ID"));
	//			requestModel.setFamilies(messages.stream().map(message -> message.getPayload()).collect(Collectors.toList()));
	//			return requestModel;
	//		}


	//	RESERVATION CONFIRMATION CHANNEL
	//	===============================

	//	CHANNEL INPUT TO HEADER ENRICHER
	@Bean
	public MessageChannel headerEnricherChannel() {
		return new DirectChannel();
	}

	//	ADDS AUTH TOKEN TO THE HEADER
	@Bean
	@Transformer(inputChannel = "headerEnricherChannel", outputChannel = "reservationConfirmationChannel")
	public HeaderEnricher addHeadersToMessage() {
		return new HeaderEnricher(Collections.singletonMap(
				"AUTH_TOKEN", new StaticHeaderValueMessageProcessor<>("12345")));
	}

	//	INPUT TO HEADER FILTER
	@Bean
	public MessageChannel headerFilterInputChannel() {
		return new DirectChannel();
	}

	//	OUTPUT OF HEADER FILTER
	@Bean
	public MessageChannel headerFilterOutputChannel() {
		return new DirectChannel();
	}

	@Bean
	@Transformer(inputChannel = "headerFilterInputChannel", outputChannel = "headerFilterOutputChannel")
	public HeaderFilter headerFilter() {
		return new HeaderFilter("AUTH_TOKEN");
	}

	//	INPUT TO CUSTOM TRANSFORMER
	@Bean
	public MessageChannel transformerInputChannel() {
		return new DirectChannel();
	}

	//	OUTPUT OF CUSTOM TRANSFORMER
	@Bean
	public MessageChannel transormerOutputChannel() {
		return new DirectChannel();
	}

	//	CUSTOM TRANSFORMER-- CONVERTS ReservationConfirmationModel TO AuditModel
	@Transformer(inputChannel = "transformerInputChannel", outputChannel = "transormerOutputChannel")
	public AuditModel customTransformer(ReservationConfirmationModel model) {
		return new AuditModel(model.getConfirmationId(), model.getName(), model.getFamilies());
	}

	@Bean
	public MessageChannel httpOutRequest() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel = "httpOutRequest") 
	@Bean
	public HttpRequestExecutingMessageHandler fetchMessageFromExternalSystem() { 
		HttpRequestExecutingMessageHandler handler = new HttpRequestExecutingMessageHandler("https://bikewise.org/api/v2/incidents?page=1&proximity_square=100"); 
		handler.setHttpMethod(HttpMethod.GET); 
		handler.setExpectedResponseType(String.class); 
		return handler; 
	}

	@Bean
	public MessageChannel httpRequest() {
		return new DirectChannel();
	}

	@Bean 
	public HttpRequestHandlingMessagingGateway inbound() { 
		HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true); 
		gateway.setRequestMapping(mapping()); 
		gateway.setRequestPayloadTypeClass(String.class); 
		gateway.setRequestChannelName("httpRequest"); 
		return gateway; 
	} 
	@Bean public RequestMapping mapping() { 
		RequestMapping requestMapping = new RequestMapping(); 
		requestMapping.setPathPatterns("/api/v1/msgFromExternalSm"); 
		requestMapping.setMethods(HttpMethod.GET); 
		return requestMapping; 
	}

	@Bean
	public MessageChannel jsonToObjInput() {
		return new DirectChannel();
	}

	//	MESSAGE ROUTED TO RESERVATION CONFIRMATION CHANNEL BY ROUTER
	@Bean
	public MessageChannel jsonToObjOutput() {
		return new DirectChannel();
	}

	@Bean
	@Transformer(inputChannel = "jsonToObjInput", outputChannel = "jsonToObjOutput")
	public JsonToObjectTransformer jsonToObjTransformer() {
		return new JsonToObjectTransformer(IncidentsClass.class);
	}
}
