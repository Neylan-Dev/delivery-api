package com.neylandev.delivery.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
