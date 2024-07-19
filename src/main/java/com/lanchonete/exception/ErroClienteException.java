package com.lanchonete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroClienteException extends RuntimeException{
    public ErroClienteException(String entidade) {
        super(String.format("%S campo incorreto", entidade));
    }
}
