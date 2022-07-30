package com.neylandev.delivery.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class DeliveryEmailDto {

    private String clientName;
    private String clientEmail;
    private String bodyMessage;

}
