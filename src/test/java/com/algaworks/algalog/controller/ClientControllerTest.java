package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.dto.ClientRequestDto;
import com.algaworks.algalog.domain.dto.ClientResponseDto;
import com.algaworks.algalog.domain.enums.DataForBusinessException;
import com.algaworks.algalog.domain.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest extends BaseIntegrationTest {

    private final static String URI = "/clients";
    private final static String VALID_EMAIL = "teste@teste.com";
    private final static String VALID_NAME = "Teste dos Santos";
    private final static String VALID_TELEPHONE = "73981213245";
    private final static Long VALID_ID = 1L;
    private final static String INVALID_EMAIL = "teste@teste";
    private final static String INVALID_SIZE_EMAIL = "testedvkjgfsjfdhjfgkjsrutwrofsogdfgirugfngkjfhfdjgtru545433iu4543kj3h63k4j5hj4363kj5bgfgdghegerogefgjdfjkfdhdfkjgfhgdfkdhjdflgkjgdflkgjdfjdghlkdfjhlkdhjoithuioyure09t34875094852496783670945842509gfgjdflkgjtiojrgegejrgoiefghdhgoiuerhg3r98t309t3t83945843teoifgkljdfgj3598gjoiejglkfdgjeg@teste.com";
    private final static String INVALID_SIZE_NAME = "te";
    private final static String INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE = "Teeee";
    private final static String INVALID_NAME_WITH_SPECIAL_CHARACTERS = "Teste2";
    private final static String INVALID_TELEPHONE = "739812132";
    private final static Long INVALID_ID = 0L;

    @MockBean
    private ClientService clientService;

    @Test
    void shouldReturnAllClients() throws Exception {

        ClientResponseDto clientResponseDto = ClientResponseDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .id(VALID_ID)
                .build();

        when(clientService.findAll()).thenReturn(Collections.singletonList(clientResponseDto));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(VALID_ID));

    }

    @Test
    void shouldReturnClientResponseDto_whenClientIdFound() throws Exception {

        ClientResponseDto clientResponseDto = ClientResponseDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .id(VALID_ID)
                .build();

        when(clientService.findById(VALID_ID)).thenReturn(clientResponseDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/{clientId}", VALID_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(VALID_ID));

    }

    @Test
    void shouldThrowBusinessException_whenClientIdNotFound() throws Exception {

        when(clientService.findById(INVALID_ID))
                .thenThrow(DataForBusinessException.CLIENT_NOT_FOUND.asBusinessExceptionWithDescriptionFormatted(Long.toString(INVALID_ID)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI + "/{clientId}", INVALID_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(DataForBusinessException.CLIENT_NOT_FOUND.getMessage()));

    }

    @Test
    void shouldSaveClientAndReturnClientResponse_whenClientRequestDtoValidWasPassed() throws Exception {

        ClientResponseDto clientResponseDto = ClientResponseDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .id(VALID_ID)
                .build();

        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .build();

        when(clientService.create(any(ClientRequestDto.class))).thenReturn(clientResponseDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(VALID_ID));

    }

    @Test
    void shouldThrowBusinessException_whenClientRequestDtoWithNameNullWasPassed() throws Exception {

        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
                .email(VALID_EMAIL)
                .telephone(VALID_TELEPHONE)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome não pode ser nulo]"));

    }

    @Test
    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithSizeInvalidWasPassed() throws Exception {

        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
                .email(VALID_EMAIL)
                .name(INVALID_SIZE_NAME)
                .telephone(VALID_TELEPHONE)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome não deve ter menos que 3 ou mais que 100 caracteres]"));

    }

    @Test
    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithFourEqualsCharactersInSequenceWasPassed() throws Exception {

        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
                .email(VALID_EMAIL)
                .name(INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE)
                .telephone(VALID_TELEPHONE)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome='"+INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE+"' não pode conter caracteres especiais ou números, ou conter 4 caracteres iguais em sequencia]"));

    }

}