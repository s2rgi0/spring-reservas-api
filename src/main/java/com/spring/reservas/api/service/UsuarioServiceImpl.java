package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.UsuarioRequestDto;
import com.spring.reservas.api.dto.UsuarioResponseDto;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    @Override
    public UsuarioResponseDto createUsuario(UsuarioRequestDto usuarioRequestDto) {
        return null;
    }

    @Override
    public UsuarioResponseDto findById(long id) {
        return null;
    }

    @Override
    public UsuarioResponseDto updateUsuario(UsuarioRequestDto usuarioRequestDto) {
        return null;
    }

    @Override
    public List<UsuarioResponseDto> findAllUsuarios() {
        return List.of();
    }
}
