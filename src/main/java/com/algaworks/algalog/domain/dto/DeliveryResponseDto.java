package com.algaworks.algalog.domain.dto;

import com.algaworks.algalog.domain.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponseDto {

    private Long id;
    private Long clientId;
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
