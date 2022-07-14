package com.algaworks.algalog.domain;

import com.algaworks.algalog.domain.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryDto {

    private Long id;
    @NotNull
    private Long clientId;
    @NotBlank
    private String recipientName;
    @NotBlank
    private String recipientStreet;
    @NotBlank
    private String recipientNumber;
    private String recipientComplement;
    @NotBlank
    private String recipientNeighborhood;
    @NotNull
    private BigDecimal tax;
    @Null
    private DeliveryStatus deliveryStatus;
    @Null
    private OffsetDateTime orderedDate;
    @Null
    private OffsetDateTime endDate;

}
