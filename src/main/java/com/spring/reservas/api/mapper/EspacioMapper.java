package com.spring.reservas.api.mapper;

import com.spring.reservas.api.dto.EspacioRequestDto;
import com.spring.reservas.api.dto.EspacioResponseDto;
import com.spring.reservas.api.entity.Espacio;

public class EspacioMapper {

    public static Espacio mapToJpa(EspacioRequestDto espacio) {

        return Espacio.builder()
                .nombre(espacio.getNombre())
                .tipo(espacio.getTipo())
                .capacidad(espacio.getCapacidad())
                .ubicacion(espacio.getUbicacion())
                .tarifaHora(espacio.getTarifaHora())
                .build();
    }

    public static EspacioResponseDto mapToDto(Espacio espacio) {

        return EspacioResponseDto.builder()
                .id(espacio.getId())
                .nombre(espacio.getNombre())
                .tipo(espacio.getTipo())
                .capacidad(espacio.getCapacidad())
                .ubicacion(espacio.getUbicacion())
                .tarifaHora(espacio.getTarifaHora())
                .build();
    }



}
