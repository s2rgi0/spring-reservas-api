package com.spring.reservas.api.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservaRequestDto {

    @NotBlank
    private String idEspacio;
    @NotBlank
    private LocalDateTime fechaInicio;
    @NotBlank
    private LocalDateTime fechaFin;


}
