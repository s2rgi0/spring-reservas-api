package com.spring.reservas.api.controller;


import com.spring.reservas.api.dto.ReporteEspacioDto;
import com.spring.reservas.api.dto.ReporteEspacioRequestDto;
import com.spring.reservas.api.service.ReporteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
@Tag(name = "Reporte de Espacios", description = "Endpoints que genera porcentajde de ocupacion de los espacios en un periodo de tiempo")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/espacios")
    public ResponseEntity<List<ReporteEspacioDto>> getReporteEspacios(@RequestBody ReporteEspacioRequestDto requestDto) {

        return ResponseEntity.ok(reporteService.getReportEspacios(requestDto.getStart(), requestDto.getEnd()));
    }


}
