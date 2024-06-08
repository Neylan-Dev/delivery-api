package com.neylandev.delivery.application.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceRequestDto {

    @NotBlank
    @Schema(description = "Descrição", example = "Destinatário não estava em casa")
    private String description;
}
