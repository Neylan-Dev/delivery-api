package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neylandev.delivery.domain.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDto {

    private Long id;
    private Long clientId;
    private String clientName;
    private String clientEmail;
    private String clientTelephone;
    private String recipientName;
    private String recipientStreet;
    private String recipientNumber;
    private String recipientComplement;
    private String recipientNeighborhood;
    private BigDecimal tax;
    private DeliveryStatus deliveryStatus;
    private OffsetDateTime orderedDate;
    private OffsetDateTime endDate;
}
