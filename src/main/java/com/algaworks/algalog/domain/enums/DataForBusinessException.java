package com.algaworks.algalog.domain.enums;

import com.algaworks.algalog.application.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum DataForBusinessException {

    INVALID_INPUT("Entrada de dados inválida", null, HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT_EXCEPTION("Argumento informado não é válido.", null, HttpStatus.BAD_REQUEST),
    CLIENT_NOT_FOUND("Cliente não encontrado", "clientId=%s", HttpStatus.NOT_FOUND),
    DELIVERY_NOT_FOUND("Entrega não encontrada", "deliveryId=%s", HttpStatus.NOT_FOUND),
    CLIENT_DELIVERY_NOT_FOUND("Cliente da entrega não encontrado", "clientId=%s", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS("Esse email já foi cadastrado", "email=%s", HttpStatus.BAD_REQUEST),
    TELEPHONE_EXISTS("Esse telefone já foi cadastrado", "telefone=%s", HttpStatus.BAD_REQUEST);


    private String message;
    private String description;
    private HttpStatus statusCode;

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatusCode()).message(this.getMessage()).build();
    }

    public BusinessException asBusinessExceptionWithDescriptionFormatted(String formatDescription) {
        return BusinessException.builder().httpStatusCode(this.getStatusCode()).message(this.getMessage()).description(formatDescription(formatDescription)).build();
    }

    public BusinessException asBusinessExceptionWithDescription(String description) {
        return BusinessException.builder().httpStatusCode(this.getStatusCode()).message(this.getMessage()).description(description).build();
    }

    public String formatDescription(String... value) {
        return String.format(this.getDescription(), value);
    }

}
