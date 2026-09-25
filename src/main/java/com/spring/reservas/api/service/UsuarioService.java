package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.UsuarioRequestDto;
import com.spring.reservas.api.dto.UsuarioResponseDto;
import com.spring.reservas.api.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto createUsuario(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto findById(long id);
    List<UsuarioResponseDto> findAll();
    Usuario finByEmail(String email);



}
