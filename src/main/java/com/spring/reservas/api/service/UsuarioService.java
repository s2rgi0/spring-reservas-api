package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.UsuarioRequestDto;
import com.spring.reservas.api.dto.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto createUsuario(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto findById(long id);
    UsuarioResponseDto updateUsuario(UsuarioRequestDto usuarioRequestDto);
    List<UsuarioResponseDto> findAllUsuarios();

}
