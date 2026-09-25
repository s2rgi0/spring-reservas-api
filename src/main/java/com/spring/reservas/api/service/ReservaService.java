package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.ReservaRequestDto;
import com.spring.reservas.api.dto.ReservaResponseDto;
import com.spring.reservas.api.entity.Usuario;

import java.util.List;

public interface ReservaService {

    ReservaResponseDto createReserva(Long id,ReservaRequestDto reservaRequestDto);
    ReservaResponseDto findReserva(long id);
    List<ReservaResponseDto> findAllReservas();
    List<ReservaResponseDto> findAllReservasByUsuarioId(long id);
    void deleteReserva(long id, Usuario usuario);

}
