package com.spring.reservas.api.exception;

import com.spring.reservas.api.dto.UsuarioRequestDto;

public class UsuarioExistsException extends RuntimeException {

    public UsuarioExistsException(String message) {
        super(message);
    }
}
