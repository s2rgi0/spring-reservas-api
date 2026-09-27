package com.spring.reservas.api.controller;

import com.spring.reservas.api.dto.EspacioRequestDto;
import com.spring.reservas.api.dto.EspacioResponseDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.service.EspacioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacio")
@Tag(name = "Espacios", description = "Endpoints para la gestion de los espacios ")
public class EspacioController {

    private final EspacioService espacioService;

    public EspacioController(EspacioService espacioService) {
        this.espacioService = espacioService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear espacios ", description = "Solamente usuario ADMIN puede crear espacios.")
    ResponseEntity<EspacioResponseDto> saveEspacio(@Valid @RequestBody EspacioRequestDto espacioRequestDto) {

        EspacioResponseDto response = espacioService.createEspacio(espacioRequestDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar espacio", description = "actualiza las caracteristicas del espacio, solo ADMIN.")
    ResponseEntity <EspacioResponseDto> actualizarEspacio(@PathVariable Long id, @Valid @RequestBody EspacioRequestDto espacioRequestDto) {
        return ResponseEntity.ok(espacioService.updateEspacio(id, espacioRequestDto));
    }

    @GetMapping
    @Operation(summary = "Listar espacios", description = "Muestra todos los espacios disponibles.")
    ResponseEntity<List<EspacioResponseDto>> findAllEspacios() {
        List<EspacioResponseDto> response = espacioService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener espacio por Id", description = "Retorna las caracteristicas de cada espacio.")
    ResponseEntity<EspacioResponseDto> findEspacioById(@PathVariable Long id) {
        EspacioResponseDto response = espacioService.findById( id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar espacio", description = "desactiva el espacio en la Base de Datos.")
    ResponseEntity<?> deleteEspacio(@PathVariable Long id) {

        espacioService.deleteEspacio(id);
        return ResponseEntity.ok("Espacio Eliminado");
    }




}
