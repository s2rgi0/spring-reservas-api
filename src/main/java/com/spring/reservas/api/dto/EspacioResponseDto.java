package com.spring.reservas.api.dto;


import com.spring.reservas.api.enums.TipoEspacio;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class EspacioResponseDto {

    private Long id;
    private String nombre;
    private TipoEspacio tipo;
    private Integer capacidad;
    private String ubicacion;
    private BigDecimal tarifaHora;

}
