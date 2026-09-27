package com.spring.reservas.api.integration;

import static org.junit.jupiter.api.Assertions.*;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.enums.Rol;
import com.spring.reservas.api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void findUsuarioByEmail(){

        Usuario usuario = Usuario.builder()
                .nombre("Neto")
                .apellido("Alas")
                .email("usuario1@gmail.com")
                .password("password123")
                .rol(Rol.USER)
                .build();

        usuarioRepository.save(usuario);

        Optional<Usuario> usuarioConEmail = usuarioRepository.findByEmail("usuario1@gmail.com");

        assertTrue(usuarioConEmail.isPresent());

    }

}
