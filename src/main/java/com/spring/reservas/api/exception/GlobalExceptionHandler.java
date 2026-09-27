package com.spring.reservas.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🎯 Captura específicamente los errores de validación de los DTOs (jakarta.validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erroresCampos = new HashMap<>();

        // Recorremos todos los errores detectados y los guardamos en un mapa (Campo -> Mensaje)
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            erroresCampos.put(fieldName, errorMessage);
        });

        // Estructuramos una respuesta JSON limpia y profesional
        Map<String, Object> respuestaError = new HashMap<>();
        respuestaError.put("timestamp", LocalDateTime.now());
        respuestaError.put("status", HttpStatus.BAD_REQUEST.value());
        respuestaError.put("error", "Error de Validación en los Datos");
        respuestaError.put("details", erroresCampos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }

    // 🛠️ Extra: Captura tus RuntimeException personalizadas (ej: "Espacio ocupado")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> respuestaError = new HashMap<>();
        respuestaError.put("timestamp", LocalDateTime.now());
        respuestaError.put("status", HttpStatus.BAD_REQUEST.value()); // O HttpStatus.CONFLICT si lo prefieres
        respuestaError.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }

}
