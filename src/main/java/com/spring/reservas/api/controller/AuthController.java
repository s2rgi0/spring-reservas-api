package com.spring.reservas.api.controller;



import com.spring.reservas.api.dto.LoginRequestDto;
import com.spring.reservas.api.dto.LoginResponseDto;
import com.spring.reservas.api.dto.UsuarioRequestDto;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.jwt.JwtUtil;
import com.spring.reservas.api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UsuarioRequestDto usuario) {

        usuarioService.createUsuario(usuario);
        return ResponseEntity.ok("Usuario registrado correctamente");

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = jwtUtil.generateToken(usuario);

        return ResponseEntity.ok(LoginResponseDto.builder()
                .mensaje("Usuario Logueado correctamente")
                .token(token)
                .build());

    }

}
