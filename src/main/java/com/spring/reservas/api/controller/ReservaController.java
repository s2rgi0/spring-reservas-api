package com.spring.reservas.api.controller;

import com.spring.reservas.api.dto.ReservaRequestDto;
import com.spring.reservas.api.dto.ReservaResponseDto;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;


    @PostMapping
    public ResponseEntity<ReservaResponseDto> createReserva(@Valid @RequestBody ReservaRequestDto dto, @AuthenticationPrincipal Usuario usuario) {

        ReservaResponseDto response = reservaService.createReserva(usuario.getId(), dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable long id, @AuthenticationPrincipal Usuario usuario) {


        reservaService.deleteReserva(id, usuario);
        return ResponseEntity.ok("reservacion cancelada");
    }


}
