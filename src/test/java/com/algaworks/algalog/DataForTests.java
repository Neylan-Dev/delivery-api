package com.algaworks.algalog;

import com.algaworks.algalog.domain.dto.ClientRequestDto;
import com.algaworks.algalog.domain.dto.ClientResponseDto;
import com.algaworks.algalog.domain.dto.DeliveryRequestDto;
import com.algaworks.algalog.domain.dto.DeliveryResponseDto;
import com.algaworks.algalog.domain.dto.OccurrenceRequestDto;
import com.algaworks.algalog.domain.dto.OccurrenceResponseDto;
import com.algaworks.algalog.domain.enums.DeliveryStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public abstract class DataForTests {

    public final static String VALID_EMAIL = "teste@teste.com";
    public final static String VALID_NAME = "Teste dos Santos";
    public final static String VALID_TELEPHONE = "73981213245";
    public final static Long VALID_DELIVERY_ID = 1L;
    public final static Long INVALID_DELIVERY_ID = 0L;
    public final static Long VALID_CLIENT_ID = 1L;
    public final static Long INVALID_CLIENT_ID = 0L;
    public final static String VALID_CLIENT_EMAIL = "teste@teste.com";
    public final static String VALID_CLIENT_NAME = "Teste dos Santos";
    public final static String VALID_CLIENT_TELEPHONE = "73981213245";
    public final static String VALID_RECIPIENT_NAME = "Teste Name";
    public final static String VALID_RECIPIENT_STREET = "Teste Street";
    public final static String VALID_RECIPIENT_NUMBER = "1";
    public final static String VALID_RECIPIENT_COMPLEMENT = "Teste Complement";
    public final static String VALID_RECIPIENT_NEIGHBORHOOD = "Teste neighborhood";
    public final static BigDecimal VALID_TAX = new BigDecimal("1.1");
    public final static String INVALID_EMAIL = "testeteste.com";
    public final static String INVALID_SIZE_NAME = "te";
    public final static String INVALID_NAME_WITH_FOUR_EQUALS_CHARACTERS_IN_SEQUENCE = "Teeee";
    public final static String INVALID_NAME_WITH_SPECIAL_CHARACTERS = "Teste2";
    public final static String INVALID_TELEPHONE = "739812132";

    public static ClientResponseDto clientResponseDtoValid() {
        return ClientResponseDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .id(VALID_CLIENT_ID)
                .build();
    }

    public static ClientRequestDto clientRequestDtoValid() {
        return ClientRequestDto.builder()
                .email(VALID_EMAIL)
                .name(VALID_NAME)
                .telephone(VALID_TELEPHONE)
                .build();
    }

    public static DeliveryResponseDto deliveryResponseDtoValid() {
        return DeliveryResponseDto.builder()
                .id(VALID_DELIVERY_ID)
                .clientEmail(VALID_CLIENT_EMAIL)
                .clientId(VALID_CLIENT_ID)
                .clientName(VALID_CLIENT_NAME)
                .deliveryStatus(DeliveryStatus.PENDING)
                .clientTelephone(VALID_CLIENT_TELEPHONE)
                .orderedDate(OffsetDateTime.now())
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .tax(VALID_TAX)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .build();
    }

    public static DeliveryRequestDto deliveryRequestDtoValid() {
        return DeliveryRequestDto.builder()
                .clientId(VALID_CLIENT_ID)
                .recipientComplement(VALID_RECIPIENT_COMPLEMENT)
                .recipientNeighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                .recipientName(VALID_RECIPIENT_NAME)
                .recipientNumber(VALID_RECIPIENT_NUMBER)
                .tax(VALID_TAX)
                .recipientStreet(VALID_RECIPIENT_STREET)
                .build();
    }

    public static OccurrenceRequestDto occurrenceRequestDtoValid(){
        return OccurrenceRequestDto.builder()
                .description("Descrição teste")
                .build();
    }

    public static OccurrenceResponseDto occurrenceResponseDtoValid(){
        return OccurrenceResponseDto.builder()
                .description("Descrição teste")
                .registerDate(OffsetDateTime.now())
                .id(1L)
                .build();
    }
}
