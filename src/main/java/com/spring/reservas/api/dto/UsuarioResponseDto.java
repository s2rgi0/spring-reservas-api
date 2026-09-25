package com.spring.reservas.api.dto;

import com.spring.reservas.api.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioResponseDto {

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Rol rol;

}
