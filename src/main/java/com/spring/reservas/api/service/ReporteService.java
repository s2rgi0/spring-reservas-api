package com.spring.reservas.api.service;


import com.spring.reservas.api.dto.ReporteEspacioDto;
import com.spring.reservas.api.dto.ReporteEspacioRequestDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.entity.Reserva;
import com.spring.reservas.api.repository.EspacioRepository;
import com.spring.reservas.api.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    private final EspacioRepository espacioRepo;
    private final ReservaRepository reservaRepo;


    public ReporteService(EspacioRepository espacioRepo, ReservaRepository reservaRepo) {
        this.espacioRepo = espacioRepo;
        this.reservaRepo = reservaRepo;
    }

    public List<ReporteEspacioDto> getReportEspacios(LocalDateTime fechaInicio, LocalDateTime fechaFin ) {


        long totalHorasDiponibles = Duration.between(fechaInicio, fechaFin).toHours();

        if (totalHorasDiponibles < 0) {
            throw new IllegalArgumentException("La hora no puede ser menor que 0");
        }

        List<Espacio> espacios = espacioRepo.findAll();
        List<ReporteEspacioDto> reporteEspacioDtos = new ArrayList<>();

        for (Espacio espacio : espacios) {

            List<Reserva> reservas = reservaRepo.findByEspacioId(espacio.getId());

            long horasReservadas = 0;

            for (Reserva reserva : reservas) {

                if(reserva.getEstado().name().equalsIgnoreCase("CANCELADA")) {
                    continue;
                }

                LocalDateTime tiempoInicial = reserva.getFechaInicio().isAfter(fechaInicio) ? reserva.getFechaInicio() : fechaInicio;
                LocalDateTime tiempoFinal = reserva.getFechaFin().isAfter(fechaFin) ? reserva.getFechaFin() : fechaInicio;

                if(tiempoInicial.isBefore(tiempoFinal)) {
                    horasReservadas += Duration.between(tiempoInicial, tiempoFinal).toHours();
                }
            }

            BigDecimal porcentaje = BigDecimal.valueOf(horasReservadas)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalHorasDiponibles) , 2, RoundingMode.HALF_UP);


            reporteEspacioDtos.add(ReporteEspacioDto.builder()
                    .nombre(espacio.getNombre())
                    .porcentaje(porcentaje)
                    .build());
        }

        return reporteEspacioDtos;
    }


}
