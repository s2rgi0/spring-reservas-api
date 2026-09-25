package com.spring.reservas.api.dto;

import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.enums.EstadoPago;
import com.spring.reservas.api.enums.EstadoReserva;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDto {

    private Long idReserva;
    private Long idEspacio;
    private Long idUsuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private EstadoReserva estado;
    private EstadoPago estadoPago;
    private Usuario usuario;
    private Espacio espacio;
    private BigDecimal precio;

}
