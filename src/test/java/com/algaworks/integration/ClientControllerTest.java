package com.algaworks.integration;

import com.algaworks.algalog.domain.dto.ClientResponseDto;
import com.algaworks.algalog.domain.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest extends BaseIntegrationTest {

    private final static String URI = "/clients";
    private final static String VALID_EMAIL = "teste@teste.com";
    private final static String VALID_NAME = "Teste dos Santos";
    private final static String VALID_TELEPHONE = "73981213245";
    private final static String INVALID_EMAIL = "teste@teste";
    private final static String INVALID_SIZE_EMAIL = "testedvkjgfsjfdhjfgkjsrutwrofsogdfgirugfngkjfhfdjgtru545433iu4543kj3h63k4j5hj4363kj5bgfgdghegerogefgjdfjkfdhdfkjgfhgdfkdhjdflgkjgdflkgjdfjdghlkdfjhlkdhjoithuioyure09t34875094852496783670945842509gfgjdflkgjtiojrgegejrgoiefghdhgoiuerhg3r98t309t3t83945843teoifgkljdfgj3598gjoiejglkfdgjeg@teste.com";
    private final static String INVALID_SIZE_NAME = "te";
    private final static String INVALID_NAME_WITH_MORE_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE = "Teeee";
    private final static String INVALID_NAME_WITH_SPECIAL_CHARACTERS = "Teste2";
    private final static String INVALID_TELEPHONE = "739812132";

    @MockBean
    private ClientService clientService;

    @Test
    void shouldListAllClients() throws Exception {

        ClientResponseDto clientResponseDto = ClientResponseDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .id(1L)
                .build();

        when(clientService.findAll()).thenReturn(Collections.singletonList(clientResponseDto));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));

    }

}