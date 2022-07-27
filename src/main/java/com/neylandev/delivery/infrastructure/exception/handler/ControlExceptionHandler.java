package com.neylandev.delivery.infrastructure.exception.handler;

import com.neylandev.delivery.infrastructure.exception.BusinessException;
import com.neylandev.delivery.domain.enums.DataForBusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@ControllerAdvice
public class ControlExceptionHandler {

    public static final String INVALID_INPUT = "Entrada de dados inválida";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<String> fieldErrorDtos = fieldErrors.stream()
                .map(f -> f.getField().concat(":").concat(requireNonNull(f.getDefaultMessage()))).map(String::new)
                .collect(Collectors.toList());

        var businessException = DataForBusinessException.INVALID_INPUT
                .asBusinessExceptionWithDescription(fieldErrorDtos.toString());

        return ResponseEntity.status(businessException.getHttpStatusCode()).body(businessException.getOnlyBody());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    private ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {

        var constraintViolations = ex.getLocalizedMessage();
        var split = constraintViolations.split("'");

        var value = split[1];

        BusinessException businessException;

        if (split[3].contains("email")) {
            businessException = DataForBusinessException.EMAIL_EXISTS
                    .asBusinessExceptionWithDescriptionFormatted(value);
        }else if (split[3].contains("telephone")){
            businessException = DataForBusinessException.TELEPHONE_EXISTS
                    .asBusinessExceptionWithDescriptionFormatted(value);
        }else {
            businessException = DataForBusinessException.ILLEGAL_ARGUMENT_EXCEPTION.asBusinessException();
        }

        return ResponseEntity.status(businessException.getHttpStatusCode()).body(businessException.getOnlyBody());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {

        var ex = DataForBusinessException.ILLEGAL_ARGUMENT_EXCEPTION
                .asBusinessExceptionWithDescription(illegalArgumentException.getLocalizedMessage());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }


}
