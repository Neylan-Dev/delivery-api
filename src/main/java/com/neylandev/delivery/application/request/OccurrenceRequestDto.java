package com.neylandev.delivery.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceRequestDto {

    @NotBlank
    @ApiModelProperty(value = "Descrição", name = "description", dataType = "String", example = "Destinatário não estava em casa")
    private String description;
}
