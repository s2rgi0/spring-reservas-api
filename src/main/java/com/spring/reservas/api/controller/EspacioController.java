package com.spring.reservas.api.controller;

import com.spring.reservas.api.dto.EspacioRequestDto;
import com.spring.reservas.api.dto.EspacioResponseDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.service.EspacioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacio")
public class EspacioController {

    private final EspacioService espacioService;

    public EspacioController(EspacioService espacioService) {
        this.espacioService = espacioService;
    }


    @PostMapping
    ResponseEntity<EspacioResponseDto> saveEspacio(@Valid @RequestBody EspacioRequestDto espacioRequestDto) {


        EspacioResponseDto response = espacioService.createEspacio(espacioRequestDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity <EspacioResponseDto> actualizarEspacio(@PathVariable Long id, @Valid @RequestBody EspacioRequestDto espacioRequestDto) {
        return ResponseEntity.ok(espacioService.updateEspacio(id, espacioRequestDto));
    }

    @GetMapping
    ResponseEntity<List<EspacioResponseDto>> findAllEspacios() {
        List<EspacioResponseDto> response = espacioService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<EspacioResponseDto> findEspacioById(@PathVariable Long id) {
        EspacioResponseDto response = espacioService.findById( id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEspacio(@PathVariable Long id) {
        espacioService.deleteEspacio(id);
        return ResponseEntity.ok("Espacio Eliminado");
    }




}
