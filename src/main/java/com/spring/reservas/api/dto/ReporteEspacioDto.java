package com.spring.reservas.api.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteEspacioDto {

    private String nombre;
    private BigDecimal porcentaje;
}
