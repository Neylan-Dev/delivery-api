package com.neylandev.delivery;

import com.neylandev.delivery.application.request.ClientRequestDto;
import com.neylandev.delivery.application.response.ClientResponseDto;
import com.neylandev.delivery.application.request.DeliveryRequestDto;
import com.neylandev.delivery.application.response.DeliveryResponseDto;
import com.neylandev.delivery.application.request.OccurrenceRequestDto;
import com.neylandev.delivery.application.response.OccurrenceResponseDto;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import com.neylandev.delivery.domain.model.Client;
import com.neylandev.delivery.domain.model.Delivery;
import com.neylandev.delivery.domain.model.Occurrence;
import com.neylandev.delivery.domain.model.Recipient;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
    private static final OffsetDateTime NOW = OffsetDateTime.now();


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
        var delivery = Delivery.builder()
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
                .orderedDate(NOW)
                .occurrences(new ArrayList<>())
                .build();
        delivery.setOccurrences(Collections.singletonList(delivery.addAndGetOccurrence(VALID_DESCRIPTION)));
        return delivery;
    }

    public static DeliveryResponseDto deliveryResponseDtoValid() {
        return DeliveryResponseDto.builder()
                .id(VALID_DELIVERY_ID)
                .clientEmail(VALID_CLIENT_EMAIL)
                .clientId(VALID_CLIENT_ID)
                .clientName(VALID_CLIENT_NAME)
                .deliveryStatus(DeliveryStatus.PENDING)
                .clientTelephone(VALID_CLIENT_TELEPHONE)
                .orderedDate(NOW)
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
        var occurrence = deliveryValid().getOccurrences().iterator().next();
        occurrence.setId(VALID_OCCURRENCE_ID);
        return occurrence;
    }

    public static OccurrenceRequestDto occurrenceRequestDtoValid() {
        return OccurrenceRequestDto.builder()
                .description(VALID_DESCRIPTION)
                .build();
    }

    public static OccurrenceResponseDto occurrenceResponseDtoValid() {
        return OccurrenceResponseDto.builder()
                .description(VALID_DESCRIPTION)
                .registerDate(NOW)
                .deliveryId(VALID_DELIVERY_ID)
                .id(VALID_OCCURRENCE_ID)
                .build();
    }
}
