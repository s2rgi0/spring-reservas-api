package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.ReservaRequestDto;
import com.spring.reservas.api.dto.ReservaResponseDto;

import java.util.List;

public interface ReservaService {

    ReservaResponseDto createReserva(ReservaRequestDto reservaRequestDto);
    ReservaResponseDto findReserva(long id);
    ReservaResponseDto updateReserva(ReservaRequestDto reservaRequestDto);
    List<ReservaResponseDto> findAllReservas();
    List<ReservaResponseDto> findAllReservasByUsuarioId(long id);
    void deleteReserva(long id);

}
