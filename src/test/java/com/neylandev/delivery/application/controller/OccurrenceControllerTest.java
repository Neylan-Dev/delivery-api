package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.OccurrenceRequestDto;
import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.repository.DeliveryRepository;
import com.neylandev.delivery.domain.repository.OccurrenceRepository;
import com.neylandev.delivery.domain.service.ClientService;
import com.neylandev.delivery.domain.service.DeliveryCreationService;
import com.neylandev.delivery.domain.service.OccurrenceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.neylandev.delivery.DataForTests.INVALID_DELIVERY_ID;
import static com.neylandev.delivery.DataForTests.deliveryRequestDtoValid;
import static com.neylandev.delivery.DataForTests.occurrenceRequestDtoValid;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OccurrenceControllerTest extends BaseIntegrationTest {

    private final static String URI = "/deliveries/{deliveryId}/occurrences";

    private OccurrenceService occurrenceService;
    private InitialDataForTests initialDataForTests;


    @BeforeAll
    public void init() {
        occurrenceService = webApplicationContext.getBean(OccurrenceService.class);
        ClientService clientService = webApplicationContext.getBean(ClientService.class);
        DeliveryCreationService deliveryCreationService = webApplicationContext.getBean(DeliveryCreationService.class);
        OccurrenceRepository occurrenceRepository = webApplicationContext.getBean(OccurrenceRepository.class);
        DeliveryRepository deliveryRepository = webApplicationContext.getBean(DeliveryRepository.class);
        ClientRepository clientRepository = webApplicationContext.getBean(ClientRepository.class);
        initialDataForTests = new InitialDataForTests(clientService, clientRepository, deliveryCreationService, deliveryRepository, occurrenceService, occurrenceRepository);
    }

    @Test
    void shouldReturnAllOccurrencesFromDelivery() throws Exception {

        OccurrenceResponseDto occurrenceResponseDto = initialDataForTests.createOccurrence(occurrenceRequestDtoValid());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI, occurrenceResponseDto.getDeliveryId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(occurrenceResponseDto.getDeliveryId()));

        initialDataForTests.deleteOccurrence();

    }

    @Test
    void shouldThrowBusinessExceptionInAllOccurrencesFromDelivery_whenDeliveryIdNotFound() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI, INVALID_DELIVERY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldRegisterOccurrenceAndReturnOccurrenceResponse_whenOccurrenceRequestDtoValidWasPassed() throws Exception {
        var delivery = initialDataForTests.createDelivery(deliveryRequestDtoValid());

        OccurrenceRequestDto occurrenceRequestDto = occurrenceRequestDtoValid();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI, delivery.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(occurrenceRequestDto)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(delivery.getId()));

        initialDataForTests.deleteOccurrence();
    }

    @Test
    void shouldThrowBusinessExceptionInRegisterOccurrence_whenDeliveryIdNotFound() throws Exception {

        OccurrenceRequestDto occurrenceRequestDto = occurrenceRequestDtoValid();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI, INVALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(occurrenceRequestDto)))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }
}