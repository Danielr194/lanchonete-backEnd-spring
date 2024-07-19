package com.lanchonete.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
@AllArgsConstructor
public class ConflitoException extends RuntimeException{

    public ConflitoException(String message) {
        super(String.format("%S já esta em uso!", message));
    }
}
