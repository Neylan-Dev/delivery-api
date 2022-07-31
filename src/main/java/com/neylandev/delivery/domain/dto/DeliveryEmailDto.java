package com.neylandev.delivery.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEmailDto {

    private String clientName;
    private String clientEmail;
    private String bodyMessage;

}
