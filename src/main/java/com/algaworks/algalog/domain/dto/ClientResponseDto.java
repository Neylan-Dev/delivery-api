package com.algaworks.algalog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponseDto {

    private Long id;
    private String name;
    private String email;
    private String telephone;

}
