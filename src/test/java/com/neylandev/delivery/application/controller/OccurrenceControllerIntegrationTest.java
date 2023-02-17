//package com.neylandev.delivery.application.controller;
//
//import com.neylandev.delivery.application.request.OccurrenceRequestDto;
//import com.neylandev.delivery.application.response.OccurrenceResponseDto;
//import com.neylandev.delivery.domain.enums.DataForBusinessException;
//import com.neylandev.delivery.domain.repository.ClientRepository;
//import com.neylandev.delivery.domain.repository.OccurrenceRepository;
//import com.neylandev.delivery.domain.repository.OrderRepository;
//import com.neylandev.delivery.domain.service.ClientService;
//import com.neylandev.delivery.domain.service.OccurrenceService;
//import com.neylandev.delivery.domain.service.OrderService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static com.neylandev.delivery.DataForTests.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class OccurrenceControllerIntegrationTest extends BaseIntegrationTest {
//
//    private final static String URI = "/deliveries/{deliveryId}/occurrences";
//
//    private InitialDataForIntegrationTests initialDataForIntegrationTests;
//
//
//    @BeforeAll
//    public void init() {
//        OccurrenceService occurrenceService = webApplicationContext.getBean(OccurrenceService.class);
//        ClientService clientService = webApplicationContext.getBean(ClientService.class);
//        OrderService orderService = webApplicationContext.getBean(OrderService.class);
//        OccurrenceRepository occurrenceRepository = webApplicationContext.getBean(OccurrenceRepository.class);
//        OrderRepository orderRepository = webApplicationContext.getBean(OrderRepository.class);
//        ClientRepository clientRepository = webApplicationContext.getBean(ClientRepository.class);
//        initialDataForIntegrationTests = new InitialDataForIntegrationTests(clientService, clientRepository, orderService, orderRepository, occurrenceService, occurrenceRepository);
//    }
//
//    @Test
//    void shouldReturnAllOccurrencesFromDelivery() throws Exception {
//
//        OccurrenceResponseDto occurrenceResponseDto = initialDataForIntegrationTests.createOccurrence(occurrenceRequestDtoValid());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI, occurrenceResponseDto.getOrderId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(occurrenceResponseDto.getId()));
//
//        initialDataForIntegrationTests.deleteOccurrence();
//
//    }
//
//    @Test
//    void shouldThrowBusinessExceptionInAllOccurrencesFromDelivery_whenDeliveryIdNotFound() throws Exception {
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI, INVALID_DELIVERY_ID)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
//                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));
//
//    }
//
//    @Test
//    void shouldRegisterOccurrenceAndReturnOccurrenceResponse_whenOccurrenceRequestDtoValidWasPassed() throws Exception {
//        var delivery = initialDataForIntegrationTests.createDelivery(deliveryRequestDtoValid());
//
//        OccurrenceRequestDto occurrenceRequestDto = occurrenceRequestDtoValid();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI, delivery.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(occurrenceRequestDto)))
//                .andDo(print()).andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(occurrenceRequestDto.getDescription()));
//
//        initialDataForIntegrationTests.deleteOccurrence();
//    }
//
//    @Test
//    void shouldThrowBusinessExceptionInRegisterOccurrence_whenDeliveryIdNotFound() throws Exception {
//
//        OccurrenceRequestDto occurrenceRequestDto = occurrenceRequestDtoValid();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI, INVALID_DELIVERY_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(occurrenceRequestDto)))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
//                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));
//
//    }
//}