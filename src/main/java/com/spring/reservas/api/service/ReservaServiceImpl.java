package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.ReservaRequestDto;
import com.spring.reservas.api.dto.ReservaResponseDto;

import java.util.List;

public class ReservaServiceImpl implements ReservaService {
    @Override
    public ReservaResponseDto createReserva(ReservaRequestDto reservaRequestDto) {
        return null;
    }

    @Override
    public ReservaResponseDto findReserva(long id) {
        return null;
    }

    @Override
    public ReservaResponseDto updateReserva(ReservaRequestDto reservaRequestDto) {
        return null;
    }

    @Override
    public List<ReservaResponseDto> findAllReservas() {
        return List.of();
    }

    @Override
    public List<ReservaResponseDto> findAllReservasByUsuarioId(long id) {
        return List.of();
    }

    @Override
    public void deleteReserva(long id) {

    }
}
