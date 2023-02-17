//package com.neylandev.delivery.application.controller;
//
//import com.neylandev.delivery.application.request.ClientRequestDto;
//import com.neylandev.delivery.domain.enums.DataForBusinessException;
//import com.neylandev.delivery.domain.service.ClientService;
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
//class ClientControllerIntegrationTest extends BaseIntegrationTest {
//
//    private final static String URI = "/clients";
//
//    private ClientService clientService;
//    private InitialDataForIntegrationTests initialDataForIntegrationTests;
//
//    @BeforeAll
//    public void init() {
//        clientService = webApplicationContext.getBean(ClientService.class);
//        initialDataForIntegrationTests = new InitialDataForIntegrationTests(clientService);
//    }
//
//    @Test
//    void shouldReturnAllClients() throws Exception {
//        var clientResponseDto = initialDataForIntegrationTests.createClient(clientRequestDtoValid());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(clientResponseDto.getId()));
//
//        initialDataForIntegrationTests.deleteClient(clientResponseDto.getId());
//    }
//
//    @Test
//    void shouldReturnClientResponseDto_whenClientIdFound() throws Exception {
//        var clientResponseDto = initialDataForIntegrationTests.createClient(clientRequestDtoValid());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI + "/{clientId}", clientResponseDto.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientResponseDto.getId()));
//
//        initialDataForIntegrationTests.deleteClient(clientResponseDto.getId());
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientIdNotFound() throws Exception {
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get(URI + "/{clientId}", INVALID_CLIENT_ID)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
//                        .value(DataForBusinessException.CLIENT_NOT_FOUND.getMessage()));
//
//    }
//
//    @Test
//    void shouldSaveClientAndReturnClientResponse_whenClientRequestDtoValidWasPassed() throws Exception {
//
//        ClientRequestDto clientRequestDto = clientRequestDtoValid();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(clientRequestDto.getName()));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameNullWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithSizeInvalidWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(INVALID_SIZE_NAME)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome não deve ter menos que 3 ou mais que 100 caracteres]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithFourEqualsCharactersInSequenceWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome='" + INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE + "' não pode conter caracteres especiais ou números, ou conter 4 caracteres iguais em sequencia]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithSpecialCharactersWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(INVALID_NAME_WITH_SPECIAL_CHARACTERS)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome='" + INVALID_NAME_WITH_SPECIAL_CHARACTERS + "' não pode conter caracteres especiais ou números, ou conter 4 caracteres iguais em sequencia]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithEmailNullWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .name(VALID_NAME)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[email:O email não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithEmailInvalidWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(INVALID_EMAIL)
//                .name(VALID_NAME)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[email:O email='" + INVALID_EMAIL + "' é inválido]"));
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithTelephoneNullWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .name(VALID_NAME)
//                .email(VALID_EMAIL)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[telephone:O telefone não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithTelephoneInvalidWasPassedAndCreateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(VALID_NAME)
//                .telephone(INVALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[telephone:O telefone='" + INVALID_TELEPHONE + "' é inválido]"));
//    }
//
//    @Test
//    void shouldUpdateClientAndReturnClientResponse_whenClientRequestDtoValidWasPassed() throws Exception {
//
//        var clientResponseDto = initialDataForIntegrationTests.createClient(clientRequestDtoValid());
//
//        ClientRequestDto clientRequestDto = clientRequestDtoValid();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", clientResponseDto.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clientResponseDto.getId()));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoValidAndClientIdNotFoundWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = clientRequestDtoValid();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", INVALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.CLIENT_NOT_FOUND.getMessage()));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameNullWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithSizeInvalidWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(INVALID_SIZE_NAME)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome não deve ter menos que 3 ou mais que 100 caracteres]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithFourEqualsCharactersInSequenceWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome='" + INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE + "' não pode conter caracteres especiais ou números, ou conter 4 caracteres iguais em sequencia]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithNameWithSpecialCharactersWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(INVALID_NAME_WITH_SPECIAL_CHARACTERS)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[name:O nome='" + INVALID_NAME_WITH_SPECIAL_CHARACTERS + "' não pode conter caracteres especiais ou números, ou conter 4 caracteres iguais em sequencia]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithEmailNullWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .name(VALID_NAME)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[email:O email não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithEmailInvalidWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(INVALID_EMAIL)
//                .name(VALID_NAME)
//                .telephone(VALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[email:O email='" + INVALID_EMAIL + "' é inválido]"));
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithTelephoneNullWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .name(VALID_NAME)
//                .email(VALID_EMAIL)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[telephone:O telefone não pode ser nulo]"));
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientRequestDtoWithTelephoneInvalidWasPassedAndUpdateWasCalled() throws Exception {
//
//        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
//                .email(VALID_EMAIL)
//                .name(VALID_NAME)
//                .telephone(INVALID_TELEPHONE)
//                .build();
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.put(URI + "/{clientId}", VALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(clientRequestDto)))
//                .andDo(print()).andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.INVALID_INPUT.getMessage()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("[telephone:O telefone='" + INVALID_TELEPHONE + "' é inválido]"));
//    }
//
//    @Test
//    void shouldDeleteClient_whenClientIdWasFound() throws Exception {
//
//        var clientResponseDto = initialDataForIntegrationTests.createClient(clientRequestDtoValid());
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.delete(URI + "/{clientId}", clientResponseDto.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNoContent());
//
//    }
//
//    @Test
//    void shouldThrowBusinessException_whenClientIdNotFoundWasPassedAndDeleteWasCalled() throws Exception {
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.delete(URI + "/{clientId}", INVALID_CLIENT_ID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print()).andExpect(status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(DataForBusinessException.CLIENT_NOT_FOUND.getMessage()));
//
//    }
//
//}