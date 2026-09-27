package com.spring.reservas.api.controller;

import com.spring.reservas.api.dto.ReservaRequestDto;
import com.spring.reservas.api.dto.ReservaResponseDto;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Endpoints para crear obtener o cancelar reservas ")
public class ReservaController {

    private final ReservaService reservaService;


    @PostMapping
    @Operation(summary = "Crear Reserva", description = "Obtiene la logica para crear una nueva reservacion.")
    public ResponseEntity<ReservaResponseDto> createReserva(@Valid @RequestBody ReservaRequestDto dto, @AuthenticationPrincipal Usuario usuario) {

        ReservaResponseDto response = reservaService.createReserva(usuario.getId(), dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar reservas", description = "Muestra reservas del usuario o todas para ADMIN.")
    public ResponseEntity<List<ReservaResponseDto>> getAllReservas(@AuthenticationPrincipal Usuario usuario) {
        if("ADMIN".equals(usuario.getRol().name())){
            return ResponseEntity.ok(reservaService.findAllReservas());
        }
        return ResponseEntity.ok(reservaService.findAllReservasByUsuarioId(usuario.getId()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reserva por ID", description = "Devuele las caracteristicas de una reserva dependiendo del usuario.")
    public ResponseEntity<ReservaResponseDto> getReservaById(@PathVariable long id, @AuthenticationPrincipal Usuario usuario) {

        ReservaResponseDto reserva = reservaService.findReserva(id);

        if(!"ADMIN".equals(usuario.getRol().name()) && reserva.getIdUsuario().equals(usuario.getId()) ){
            return ResponseEntity.ok(reserva);
        }
        return ResponseEntity.ok(reserva);

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar reserva ", description = "Mueve el estado de la reserva a CANCELADA.")
    public ResponseEntity<?> deleteReserva(@PathVariable long id, @AuthenticationPrincipal Usuario usuario) {

        reservaService.deleteReserva(id, usuario);
        return ResponseEntity.ok("reservacion cancelada");
    }


}
