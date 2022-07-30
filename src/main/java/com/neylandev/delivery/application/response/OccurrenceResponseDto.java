package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceResponseDto {

    @ApiModelProperty(value = "Id da ocorrência", name = "id", dataType = "Long", example = "1")
    private Long id;
    @ApiModelProperty(value = "Descrição", name = "description", dataType = "String", example = "Destinatário não estava em casa")
    private String description;
    @ApiModelProperty(value = "Id da entrega", name = "id", dataType = "Long", example = "1")
    private Long deliveryId;
    @ApiModelProperty(value = "Data do ocorrência", name = "registerDate", dataType = "OffsetDateTime", example = "2022-07-28T11:00:03.831798-03:00")
    private OffsetDateTime registerDate;
}
