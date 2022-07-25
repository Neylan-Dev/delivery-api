package com.algaworks.algalog;

import com.algaworks.algalog.domain.dto.ClientRequestDto;
import com.algaworks.algalog.domain.dto.ClientResponseDto;
import com.algaworks.algalog.domain.dto.DeliveryRequestDto;
import com.algaworks.algalog.domain.dto.DeliveryResponseDto;
import com.algaworks.algalog.domain.dto.OccurrenceRequestDto;
import com.algaworks.algalog.domain.dto.OccurrenceResponseDto;
import com.algaworks.algalog.domain.enums.DeliveryStatus;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Occurrence;
import com.algaworks.algalog.domain.model.Recipient;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;

public abstract class DataForTests {

    public final static String VALID_EMAIL = "teste@teste.com";
    public final static String VALID_NAME = "Teste dos Santos";
    public final static String VALID_TELEPHONE = "73981213245";
    public final static Long VALID_DELIVERY_ID = 1L;
    public final static Long INVALID_DELIVERY_ID = 0L;
    public final static Long VALID_CLIENT_ID = 1L;
    public final static Long VALID_OCCURRENCE_ID = 1L;
    public final static String VALID_DESCRIPTION = "Descrição teste";
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


    public static Client clientValid() {
        return Client.builder()
                .email(VALID_EMAIL)
                .id(VALID_CLIENT_ID)
                .telephone(VALID_TELEPHONE)
                .name(VALID_NAME)
                .build();
    }

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


    public static Delivery deliveryValid() {
        return Delivery.builder()
                .recipient(Recipient.builder()
                        .name(VALID_RECIPIENT_NAME)
                        .complement(VALID_RECIPIENT_COMPLEMENT)
                        .neighborhood(VALID_RECIPIENT_NEIGHBORHOOD)
                        .number(VALID_RECIPIENT_NUMBER)
                        .street(VALID_RECIPIENT_STREET)
                        .build())
                .deliveryStatus(DeliveryStatus.PENDING)
                .client(clientValid())
                .tax(new BigDecimal("1.1"))
                .id(VALID_DELIVERY_ID)
                .orderedDate(OffsetDateTime.now())
                .occurrences(Collections.singletonList(occurrenceValid()))
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

    public static Occurrence occurrenceValid() {
        return Occurrence.builder()
                .description(VALID_DESCRIPTION)
                .registerDate(OffsetDateTime.now())
                .id(VALID_OCCURRENCE_ID)
                .delivery(deliveryValid())
                .build();
    }

    public static OccurrenceRequestDto occurrenceRequestDtoValid() {
        return OccurrenceRequestDto.builder()
                .description(VALID_DESCRIPTION)
                .build();
    }

    public static OccurrenceResponseDto occurrenceResponseDtoValid() {
        return OccurrenceResponseDto.builder()
                .description(VALID_DESCRIPTION)
                .registerDate(OffsetDateTime.now())
                .deliveryId(VALID_DELIVERY_ID)
                .id(VALID_OCCURRENCE_ID)
                .build();
    }
}
