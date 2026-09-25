package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.UsuarioRequestDto;
import com.spring.reservas.api.dto.UsuarioResponseDto;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.exception.UsuarioExistsException;
import com.spring.reservas.api.mapper.UsuarioMapper;
import com.spring.reservas.api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioResponseDto createUsuario(UsuarioRequestDto usuarioRequestDto) {

        if(usuarioRepository.findByEmail(usuarioRequestDto.getEmail()).isPresent()) {
            throw new UsuarioExistsException("El usuario "+usuarioRequestDto.getEmail()+" ya existe");
        }

        Usuario usuario = UsuarioMapper.mapToJpa(usuarioRequestDto);

        usuario.setPassword(passwordEncoder.encode(usuarioRequestDto.getPassword()));
        usuario.setRol(usuarioRequestDto.getRol());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return UsuarioMapper.mapToDto(savedUsuario);
    }

    @Override
    public UsuarioResponseDto findById(long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioExistsException("El usuario no existe"));

        return UsuarioMapper.mapToDto(usuario);
    }


    @Override
    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario finByEmail(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email).get();
        return usuario;
    }


}
