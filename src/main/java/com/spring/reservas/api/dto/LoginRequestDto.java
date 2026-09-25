package com.spring.reservas.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Ingresa un email")
    @Email(message = "El formato del email es invalido")
    private String email;

    @NotBlank(message = "Ingresa una contraseña")
    private String password;
}
