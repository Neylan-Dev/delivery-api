package com.neylandev.delivery.application.controller;

import com.neylandev.delivery.application.request.DeliveryRequestDto;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import com.neylandev.delivery.domain.producer.DeliverySendEmailProducer;
import com.neylandev.delivery.domain.repository.ClientRepository;
import com.neylandev.delivery.domain.repository.DeliveryRepository;
import com.neylandev.delivery.domain.service.ClientService;
import com.neylandev.delivery.domain.service.DeliveryCreationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.neylandev.delivery.DataForTests.INVALID_DELIVERY_ID;
import static com.neylandev.delivery.DataForTests.VALID_CLIENT_ID;
import static com.neylandev.delivery.DataForTests.VALID_RECIPIENT_COMPLEMENT;
import static com.neylandev.delivery.DataForTests.VALID_RECIPIENT_NAME;
import static com.neylandev.delivery.DataForTests.VALID_RECIPIENT_NEIGHBORHOOD;
import static com.neylandev.delivery.DataForTests.VALID_RECIPIENT_NUMBER;
import static com.neylandev.delivery.DataForTests.VALID_RECIPIENT_STREET;
import static com.neylandev.delivery.DataForTests.VALID_TAX;
import static com.neylandev.delivery.DataForTests.clientRequestDtoValid;
import static com.neylandev.delivery.DataForTests.deliveryRequestDtoValid;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeliveryControllerTest extends BaseIntegrationTest {

    private final static String URI = "/deliveries";

    private ClientService clientService;
    private DeliveryCreationService deliveryCreationService;
    private DeliveryRepository deliveryRepository;
    private ClientRepository clientRepository;
    @MockBean
    private DeliverySendEmailProducer deliverySendEmailProducer;
    private InitialDataForTests initialDataForTests;

    @BeforeAll
    public void init() {
        clientService = webApplicationContext.getBean(ClientService.class);
        deliveryCreationService = webApplicationContext.getBean(DeliveryCreationService.class);
        deliveryRepository = webApplicationContext.getBean(DeliveryRepository.class);
        clientRepository = webApplicationContext.getBean(ClientRepository.class);
        initialDataForTests = new InitialDataForTests(clientService, clientRepository, deliveryCreationService, deliveryRepository);
    }

    @Test
    void shouldReturnAllDeliveries() throws Exception {
        var deliveryResponseDto = initialDataForTests.createDelivery(deliveryRequestDtoValid());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(deliveryResponseDto.getId()));

        initialDataForTests.deleteDelivery();
    }

    @Test
    void shouldReturnDeliveryResponseDto_whenDeliveryIdFound() throws Exception {
        var deliveryResponseDto = initialDataForTests.createDelivery(deliveryRequestDtoValid());

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/{deliveryId}", deliveryResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(deliveryResponseDto.getId()));

        initialDataForTests.deleteDelivery();
    }

    @Test
    void shouldThrowBusinessException_whenDeliveryIdNotFound() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/{deliveryId}", INVALID_DELIVERY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldSaveDeliveryAndReturnDeliveryResponse_whenDeliveryRequestDtoValidWasPassed() throws Exception {
        var client = initialDataForTests.createClient(clientRequestDtoValid());

        DeliveryRequestDto deliveryRequestDto = deliveryRequestDtoValid();
        deliveryRequestDto.setClientId(client.getId());

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipientName").value(deliveryRequestDto.getRecipientName()));

        initialDataForTests.deleteDelivery();
    }

    @Test
    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNameNullWasPassedAndSaveWasCalled() throws Exception {

        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .clientId(VALID_CLIENT_ID)
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .tax(VALID_TAX)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientName:O campo recipientName não pode ser nulo]"));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNeighborhoodNullWasPassedAndSaveWasCalled() throws Exception {

        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .clientId(VALID_CLIENT_ID)
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .tax(VALID_TAX)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientNeighborhood:O campo recipientNeighborhood não pode ser nulo]"));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNumberNullWasPassedAndSaveWasCalled() throws Exception {

        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .clientId(VALID_CLIENT_ID)
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .tax(VALID_TAX)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientNumber:O campo recipientNumber não pode ser nulo]"));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryRequestDtoWithRecipientNeighborStreetNullWasPassedAndSaveWasCalled() throws Exception {

        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .clientId(VALID_CLIENT_ID)
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .tax(VALID_TAX)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[recipientStreet:O campo recipientStreet não pode ser nulo]"));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryRequestDtoWithTaxNullWasPassedAndSaveWasCalled() throws Exception {

        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .clientId(VALID_CLIENT_ID)
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[tax:O campo tax não pode ser nulo]"));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryRequestDtoWithClientIdNullWasPassedAndSaveWasCalled() throws Exception {

        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .tax(VALID_TAX)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[clientId:O campo clientId não pode ser nulo]"));

    }

    @Test
    void shouldCompleteDelivery_whenDeliveryIdWasFound() throws Exception {
        var deliveryResponseDto = initialDataForTests.createDelivery(deliveryRequestDtoValid());

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/complete", deliveryResponseDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());

        initialDataForTests.deleteDelivery();
    }

    @Test
    void shouldCancelDelivery_whenDeliveryIdWasFound() throws Exception {
        var deliveryResponseDto = initialDataForTests.createDelivery(deliveryRequestDtoValid());

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/cancel", deliveryResponseDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());

        initialDataForTests.deleteDelivery();
    }

    @Test
    void shouldThrowBusinessException_whenDeliveryIdNotFoundWasPassedAndCompleteWasCalled() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/complete", INVALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryIdNotFoundWasPassedAndCancelWasCalled() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/cancel", INVALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

}