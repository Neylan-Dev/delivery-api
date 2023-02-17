//package com.neylandev.delivery.application.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.neylandev.delivery.application.request.OrderRequestDto;
//import com.neylandev.delivery.domain.dto.OrderEmailDto;
//import com.neylandev.delivery.domain.enums.DataForBusinessException;
//import com.neylandev.delivery.domain.repository.ClientRepository;
//import com.neylandev.delivery.domain.repository.OrderRepository;
//import com.neylandev.delivery.domain.service.ClientService;
//import com.neylandev.delivery.domain.service.OrderService;
//import org.apache.camel.CamelContext;
//import org.apache.camel.EndpointInject;
//import org.apache.camel.component.mock.MockEndpoint;
//import org.apache.camel.test.spring.junit5.MockEndpoints;
//import org.apache.camel.test.spring.junit5.UseAdviceWith;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static com.neylandev.delivery.DataForTests.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@MockEndpoints
//@UseAdviceWith
//class OrderControllerIntegrationTest extends BaseIntegrationTest {
//
//    private final static String URI = "/deliveries";
//
//    private InitialDataForIntegrationTests initialDataForIntegrationTests;
//
//    @Autowired
//    CamelContext camelContext;
//
//    @EndpointInject("{{to.delivery.email}}")
//    MockEndpoint deliverySendEmailMockEndpoint;
//
//    @BeforeAll
//    public void init() {
//        ClientService clientService = webApplicationContext.getBean(ClientService.class);
//        OrderService orderService = webApplicationContext.getBean(OrderService.class);
//        OrderRepository orderRepository = webApplicationContext.getBean(OrderRepository.class);
//        ClientRepository clientRepository = webApplicationContext.getBean(ClientRepository.class);
//        initialDataForIntegrationTests = new InitialDataForIntegrationTests(clientService, clientRepository, orderService, orderRepository);
//    }
//
//    @Test
//    void shouldReturnAllDeliveries() throws Exception {
//        var deliveryResponseDto = initialDataForIntegrationTests.createDelivery(deliveryRequestDtoValid());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(deliveryResponseDto.getId()));
//
//        initialDataForIntegrationTests.deleteDelivery();
//    }
//
//    @Test
//    void shouldReturnDeliveryResponseDto_whenDeliveryIdFound() throws Exception {
//        var deliveryResponseDto = initialDataForIntegrationTests.createDelivery(deliveryRequestDtoValid());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI + "/{deliveryId}", deliveryResponseDto.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(deliveryResponseDto.getId()));
//
//        initialDataForIntegrationTests.deleteDelivery();
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryIdNotFound() throws Exception {
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI + "/{deliveryId}", INVALID_DELIVERY_ID)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
//                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));
//
//    }
//
//    @Test
//    void shouldSaveDeliveryAndReturnDeliveryResponse_whenDeliveryRequestDtoValidWasPassed() throws Exception {
//        var client = initialDataForIntegrationTests.createClient(clientRequestDtoValid());
//
//        OrderRequestDto orderRequestDto = deliveryRequestDtoValid();
//        orderRequestDto.setClientId(client.getId());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.recipientName").value(orderRequestDto.getRecipientName()));
//
//        initialDataForIntegrationTests.deleteDelivery();
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNameNullWasPassedAndSaveWasCalled() throws Exception {
//
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .clientId(VALID_CLIENT_ID)
//                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
//                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
//                .recipientNumber(VALID_RECIPIENT_NUMBER)
//                .recipientStreet(VALID_RECIPIENT_STREET)
//                .tax(VALID_TAX)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientName:O campo recipientName não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNeighborhoodNullWasPassedAndSaveWasCalled() throws Exception {
//
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .clientId(VALID_CLIENT_ID)
//                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
//                .recipientName(VALID_RECIPIENT_NAME)
//                .recipientNumber(VALID_RECIPIENT_NUMBER)
//                .recipientStreet(VALID_RECIPIENT_STREET)
//                .tax(VALID_TAX)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientNeighborhood:O campo recipientNeighborhood não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNumberNullWasPassedAndSaveWasCalled() throws Exception {
//
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .clientId(VALID_CLIENT_ID)
//                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
//                .recipientName(VALID_RECIPIENT_NAME)
//                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
//                .recipientStreet(VALID_RECIPIENT_STREET)
//                .tax(VALID_TAX)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientNumber:O campo recipientNumber não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNeighborStreetNullWasPassedAndSaveWasCalled() throws Exception {
//
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .clientId(VALID_CLIENT_ID)
//                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
//                .recipientName(VALID_RECIPIENT_NAME)
//                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
//                .recipientNumber(VALID_RECIPIENT_NUMBER)
//                .tax(VALID_TAX)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientStreet:O campo recipientStreet não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryRequestDtoWithTaxNullWasPassedAndSaveWasCalled() throws Exception {
//
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .clientId(VALID_CLIENT_ID)
//                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
//                .recipientName(VALID_RECIPIENT_NAME)
//                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
//                .recipientNumber(VALID_RECIPIENT_NUMBER)
//                .recipientStreet(VALID_RECIPIENT_STREET)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[tax:O campo tax não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryRequestDtoWithClientIdNullWasPassedAndSaveWasCalled() throws Exception {
//
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
//                .recipientName(VALID_RECIPIENT_NAME)
//                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
//                .recipientNumber(VALID_RECIPIENT_NUMBER)
//                .recipientStreet(VALID_RECIPIENT_STREET)
//                .tax(VALID_TAX)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(orderRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[clientId:O campo clientId não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldCompleteDeliveryAndSendEmail_whenDeliveryIdWasFound() throws Exception {
//        camelContext.start();
//
//        var deliveryResponseDto = initialDataForIntegrationTests.createDelivery(deliveryRequestDtoValid());
//
//        var deliveryEmailDto = DeliveryEmailDto.builder()
//                .clientEmail(deliveryResponseDto.getClientEmail())
//                .subject("Produto recebido com sucesso")
//                .body(String.format("O produto de %s foi recebido por %s", deliveryResponseDto.getClientName(), deliveryResponseDto.getRecipientName()))
//                .build();
//
//        deliverySendEmailMockEndpoint.reset();
//        deliverySendEmailMockEndpoint.expectedMessageCount(1);
//        deliverySendEmailMockEndpoint.expectedBodiesReceived(new ObjectMapper().writeValueAsString(deliveryEmailDto));
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/complete", deliveryResponseDto.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNoContent());
//
//        deliverySendEmailMockEndpoint.assertIsSatisfied();
//
//        initialDataForIntegrationTests.deleteDelivery();
//    }
//
//    @Test
//    void shouldCancelDeliveryAndSendEmail_whenDeliveryIdWasFound() throws Exception {
//        camelContext.start();
//
//        var deliveryResponseDto = initialDataForIntegrationTests.createDelivery(deliveryRequestDtoValid());
//
//        var deliveryEmailDto = DeliveryEmailDto.builder()
//                .clientEmail(deliveryResponseDto.getClientEmail())
//                .subject("O envio do produto foi cancelado")
//                .body(String.format("O produto de %s não pode ser enviado", deliveryResponseDto.getClientName()))
//                .build();
//
//        deliverySendEmailMockEndpoint.reset();
//        deliverySendEmailMockEndpoint.expectedMessageCount(1);
//        deliverySendEmailMockEndpoint.expectedBodiesReceived(new ObjectMapper().writeValueAsString(deliveryEmailDto));
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/cancel", deliveryResponseDto.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNoContent());
//
//        deliverySendEmailMockEndpoint.assertIsSatisfied();
//
//        initialDataForIntegrationTests.deleteDelivery();
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryIdNotFoundWasPassedAndCompleteWasCalled() throws Exception {
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/complete", INVALID_DELIVERY_ID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenDeliveryIdNotFoundWasPassedAndCancelWasCalled() throws Exception {
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/cancel", INVALID_DELIVERY_ID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));
//
//    }
//
//}