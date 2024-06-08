package com.neylandev.delivery.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceResponseDto {

    @Schema(description = "Id da ocorrência", example = "1")
    private Long id;
    @Schema(description = "Descrição", example = "Destinatário não estava em casa")
    private String description;
    @Schema(description = "Id do produto", example = "1")
    private Long orderId;
    @Schema(description = "Data do ocorrência", example = "2022-07-28T11:00:03.831798-03:00")
    private LocalDateTime registerDate;
}
