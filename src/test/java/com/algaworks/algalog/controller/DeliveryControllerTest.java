package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.dto.DeliveryRequestDto;
import com.algaworks.algalog.domain.dto.DeliveryResponseDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.service.DeliveryCompletionService;
import com.algaworks.algalog.domain.service.DeliveryCreationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static com.algaworks.algalog.DataForTests.INVALID_DELIVERY_ID;
import static com.algaworks.algalog.DataForTests.VALID_CLIENT_ID;
import static com.algaworks.algalog.DataForTests.VALID_DELIVERY_ID;
import static com.algaworks.algalog.DataForTests.VALID_RECIPIENT_COMPLEMENT;
import static com.algaworks.algalog.DataForTests.VALID_RECIPIENT_NAME;
import static com.algaworks.algalog.DataForTests.VALID_RECIPIENT_NEIGHBORHOOD;
import static com.algaworks.algalog.DataForTests.VALID_RECIPIENT_NUMBER;
import static com.algaworks.algalog.DataForTests.VALID_RECIPIENT_STREET;
import static com.algaworks.algalog.DataForTests.VALID_TAX;
import static com.algaworks.algalog.DataForTests.deliveryRequestDtoValid;
import static com.algaworks.algalog.DataForTests.deliveryResponseDtoValid;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeliveryControllerTest extends BaseIntegrationTest {

    private final static String URI = "/deliveries";

    @MockBean
    private DeliveryCompletionService deliveryCompletionService;
    @MockBean
    private DeliveryCreationService deliveryCreationService;

    @Test
    void shouldReturnAllDeliveries() throws Exception {

        DeliveryResponseDto deliveryResponseDto = deliveryResponseDtoValid();

        when(deliveryCreationService.findAll()).thenReturn(Collections.singletonList(deliveryResponseDto));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(deliveryResponseDto.getId()));

    }

    @Test
    void shouldReturnDeliveryResponseDto_whenDeliveryIdFound() throws Exception {

        DeliveryResponseDto deliveryResponseDto = deliveryResponseDtoValid();

        when(deliveryCreationService.findById(deliveryResponseDto.getId())).thenReturn(deliveryResponseDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/{deliveryId}", deliveryResponseDto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(deliveryResponseDto.getId()));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryIdNotFound() throws Exception {

        when(deliveryCreationService.findById(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/{deliveryId}", INVALID_DELIVERY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldSaveDeliveryAndReturnDeliveryResponse_whenDeliveryRequestDtoValidWasPassed() throws Exception {

        DeliveryResponseDto deliveryResponseDto = deliveryResponseDtoValid();

        DeliveryRequestDto deliveryRequestDto = deliveryRequestDtoValid();

        when(deliveryCreationService.save(any(DeliveryRequestDto.class))).thenReturn(deliveryResponseDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(deliveryRequestDto)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(VALID_DELIVERY_ID));

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

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/complete", VALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());

    }

    @Test
    void shouldCancelDelivery_whenDeliveryIdWasFound() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/cancel", VALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryIdNotFoundWasPassedAndCompleteWasCalled() throws Exception {

        doThrow(DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID))).when(deliveryCompletionService).complete(anyLong());

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/complete", INVALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldThrowBusinessException_whenDeliveryIdNotFoundWasPassedAndCancelWasCalled() throws Exception {

        doThrow(DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID))).when(deliveryCompletionService).cancel(anyLong());

        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI + "/{deliveryId}/cancel", INVALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

}