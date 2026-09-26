package com.spring.reservas.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservaRequestDto {

    @NotNull(message = "El id del espacio no puede ser nulo")
    private Long idEspacio;
    @NotNull(message = "La fecha inicio no puede ser nula")
    private LocalDateTime fechaInicio;
    @NotNull(message = "La fecha final no puede ser nula")
    private LocalDateTime fechaFin;


}
