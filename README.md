# spring-integration
Spring integration example

Contains 2 http POST methods and 1 http GET method to demonstrate different components in spring integration.

1) /api/v1/partyrequest
  -request body should be "ReservationRequestModel"
  
  the flow of data will be as follows:
  partyrequest -> RequestGateway -> requestChannel -> **PayloadTypeRouter** -> reservationRequestChannel-> **MessageSelector (filter)** -> reservationReqFilteredChannel 
    -> ReservationRequestService -> FamilyDataGateway -> familyProcessChannel -> **familySplitter** -> familyProcessSplitterChannel -> FamilyService -> AggregatorGateway 
    -> aggregatorSendChannel -> **AdvancedAggregator** -> aggregatorRecieverChannel -> AggregatorService
 
 2) /api/v1/partyconfirmation
  -request body should be "ReservationConfirmationModel"
  
  the flow of data will be as follows:
  partyConfirmation -> RequestGateway -> requestChannel -> **PayloadTypeRouter** -> headerEnricherChannel -> **HeaderEnricher** -> reservationConfirmationChannel 
    -> ReservationConfirmationService -> HeaderFilterGateway -> headerFilterInputChannel -> **HeaderFilter** -> headerFilterOutputChannel -> MachineLearningService 
    -> TransformerGateway -> transformerInputChannel -> **customTransformer** -> transormerOutputChannel -> AuditService
 
 3) /api/v1/msgFromExternalSm
  -GET method to demonstrate Http inbound and outbound gateways. inbound gateway recieves the request rather than controller and outbound gateway sends message to external systems.
  
  data flow is given below:
    **HttpRequestHandlingMessagingGateway** -> httpRequest -> InboundMsgService -> HttpRestInegrationGateway -> httpOutRequest -> **HttpRequestExecutingMessageHandler**
    -> JsonToObjGateway -> jsonToObjInput -> **JsonToObjectTransformer** -> jsonToObjOutput -> InboundMsgService
