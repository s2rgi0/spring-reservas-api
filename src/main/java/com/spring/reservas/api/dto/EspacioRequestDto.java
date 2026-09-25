package com.spring.reservas.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.reservas.api.enums.TipoEspacio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EspacioRequestDto {


    private String nombre;


    private TipoEspacio tipo;


    private Integer capacidad;


    private String ubicacion;


    private BigDecimal tarifaHora;

}
