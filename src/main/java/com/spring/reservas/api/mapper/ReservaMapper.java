package com.spring.reservas.api.mapper;

import com.spring.reservas.api.dto.ReservaResponseDto;
import com.spring.reservas.api.entity.Reserva;

public class ReservaMapper {

    public static ReservaResponseDto mapToDto(Reserva reserva) {
        return ReservaResponseDto.builder()
                .idReserva(reserva.getId())
                .idUsuario(reserva.getUsuario().getId())
                .idEspacio(reserva.getEspacio().getId())
                .fechaInicio(reserva.getFechaInicio())
                .fechaFin(reserva.getFechaFin())
                .estado(reserva.getEstado())
                .estadoPago(reserva.getEstadoPago())
                .precio(reserva.getPrecio())
                .build();
    }

}
