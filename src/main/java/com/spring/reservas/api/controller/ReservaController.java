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

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ReservaResponseDto>> getAllReservas(@AuthenticationPrincipal Usuario usuario) {
        if("ADMIN".equals(usuario.getRol().name())){
            return ResponseEntity.ok(reservaService.findAllReservas());
        }
        return ResponseEntity.ok(reservaService.findAllReservasByUsuarioId(usuario.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDto> getReservaById(@PathVariable long id, @AuthenticationPrincipal Usuario usuario) {

        ReservaResponseDto reserva = reservaService.findReserva(id);

        if(!"ADMIN".equals(usuario.getRol().name()) && reserva.getUsuario().getId().equals(usuario.getId()) ){
            return ResponseEntity.ok(reserva);
        }

        return ResponseEntity.ok(reserva);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable long id, @AuthenticationPrincipal Usuario usuario) {


        reservaService.deleteReserva(id, usuario);
        return ResponseEntity.ok("reservacion cancelada");
    }


}
