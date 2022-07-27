package com.algaworks.algalog.application.controller;

import com.algaworks.algalog.application.request.OccurrenceRequestDto;
import com.algaworks.algalog.application.response.OccurrenceResponseDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.service.OccurrenceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static com.algaworks.algalog.DataForTests.INVALID_DELIVERY_ID;
import static com.algaworks.algalog.DataForTests.VALID_DELIVERY_ID;
import static com.algaworks.algalog.DataForTests.occurrenceRequestDtoValid;
import static com.algaworks.algalog.DataForTests.occurrenceResponseDtoValid;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OccurrenceControllerTest extends BaseIntegrationTest {

    private final static String URI = "/deliveries/{deliveryId}/occurrences";

    @MockBean
    private OccurrenceService occurrenceService;

    @Test
    void shouldReturnAllOccurrencesFromDelivery() throws Exception {

        OccurrenceResponseDto occurrenceResponseDto = occurrenceResponseDtoValid();

        when(occurrenceService.findAllOccurrencesOfDelivery(occurrenceResponseDto.getId())).thenReturn(Collections.singletonList(occurrenceResponseDto));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI, VALID_DELIVERY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(VALID_DELIVERY_ID));

    }

    @Test
    void shouldThrowBusinessExceptionInAllOccurrencesFromDelivery_whenDeliveryIdNotFound() throws Exception {

        when(occurrenceService.findAllOccurrencesOfDelivery(INVALID_DELIVERY_ID))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI, INVALID_DELIVERY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldRegisterOccurrenceAndReturnOccurrenceResponse_whenOccurrenceRequestDtoValidWasPassed() throws Exception {

        OccurrenceResponseDto occurrenceResponseDto = occurrenceResponseDtoValid();

        OccurrenceRequestDto occurrenceRequestDto = occurrenceRequestDtoValid();

        when(occurrenceService.registerOccurrence(anyLong(), anyString())).thenReturn(occurrenceResponseDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI, VALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(occurrenceRequestDto)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(VALID_DELIVERY_ID));

    }

    @Test
    void shouldThrowBusinessExceptionInRegisterOccurrence_whenDeliveryIdNotFound() throws Exception {

        OccurrenceRequestDto occurrenceRequestDto = occurrenceRequestDtoValid();

        when(occurrenceService.registerOccurrence(INVALID_DELIVERY_ID, occurrenceRequestDto.getDescription()))
                .thenThrow(DataForBusinessException.DELIVERY_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_DELIVERY_ID)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI, INVALID_DELIVERY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(occurrenceRequestDto)))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.DELIVERY_NOT_FOUND.getMessage()));

    }
}