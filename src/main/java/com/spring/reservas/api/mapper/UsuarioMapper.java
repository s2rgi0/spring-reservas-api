package com.spring.reservas.api.mapper;

import com.spring.reservas.api.dto.UsuarioRequestDto;
import com.spring.reservas.api.dto.UsuarioResponseDto;
import com.spring.reservas.api.entity.Usuario;

public class UsuarioMapper {

    public static Usuario mapToJpa(UsuarioRequestDto dto) {

        return Usuario.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .password(dto.getPassword()).build();
    }

    public static UsuarioResponseDto mapToDto(Usuario usuario) {
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
    }
}
