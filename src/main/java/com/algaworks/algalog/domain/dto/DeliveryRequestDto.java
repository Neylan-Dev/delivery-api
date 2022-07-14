package com.algaworks.algalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequestDto {

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
}
