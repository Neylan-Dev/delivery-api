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

    @NotNull(message = "O campo clientId não pode ser nulo")
    private Long clientId;
    @NotBlank(message = "O campo recipientName não pode ser nulo")
    private String recipientName;
    @NotBlank(message = "O campo recipientStreet não pode ser nulo")
    private String recipientStreet;
    @NotBlank(message = "O campo recipientNumber não pode ser nulo")
    private String recipientNumber;
    private String recipientComplement;
    @NotBlank(message = "O campo recipientNeighborhood não pode ser nulo")
    private String recipientNeighborhood;
    @NotNull(message = "O campo tax não pode ser nulo")
    private BigDecimal tax;
}
