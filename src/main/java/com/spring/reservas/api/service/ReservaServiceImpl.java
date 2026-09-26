package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.ReservaRequestDto;
import com.spring.reservas.api.dto.ReservaResponseDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.entity.Reserva;
import com.spring.reservas.api.entity.Usuario;
import com.spring.reservas.api.enums.EstadoPago;
import com.spring.reservas.api.enums.EstadoReserva;
import com.spring.reservas.api.event.ReservaConfirmadaEvent;
import com.spring.reservas.api.exception.EntityNotFoundException;
import com.spring.reservas.api.mapper.ReservaMapper;
import com.spring.reservas.api.repository.EspacioRepository;
import com.spring.reservas.api.repository.ReservaRepository;
import com.spring.reservas.api.repository.UsuarioRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {


    private final ReservaRepository reservaRepo;
    private final UsuarioRepository usuarioRepo;
    private final EspacioRepository espacioRepo;
    private final PagoService pagoService;
    private final ApplicationEventPublisher publisher;


    public ReservaServiceImpl(ReservaRepository repo, ReservaRepository reservaRepo, UsuarioRepository usuarioRepo, EspacioRepository espacioRepo, PagoService pagoService, ApplicationEventPublisher publisher) {

        this.reservaRepo = reservaRepo;
        this.usuarioRepo = usuarioRepo;
        this.espacioRepo = espacioRepo;
        this.pagoService = pagoService;
        this.publisher = publisher;
    }


    @Override
    @Transactional
    public ReservaResponseDto createReserva(Long id, ReservaRequestDto dto) {

        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"+id));

        Espacio espacio = espacioRepo.findById(dto.getIdEspacio())
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado"+dto.getIdEspacio()));

        List<EstadoReserva> estadosQueBloquean = List.of(EstadoReserva.PENDIENTE, EstadoReserva.CONFIRMADA);

        boolean estaOcupado = reservaRepo
                .verificarSiEspacioEstaOcupado(dto.getIdEspacio(), dto.getFechaInicio(), dto.getFechaFin(), estadosQueBloquean);

        if(estaOcupado) {
            new RuntimeException("Espacio ocupado");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setEspacio(espacio);
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());
        reserva.setEstado(EstadoReserva.PENDIENTE);
        reserva.setEstadoPago(EstadoPago.PENDIENTE);

        long hours = java.time.Duration.between(dto.getFechaInicio(), dto.getFechaFin()).toHours();
        if (hours == 0) hours = 1;
        BigDecimal total = espacio.getTarifaHora().multiply(BigDecimal.valueOf(hours));
        reserva.setPrecio(total);

        if(pagoService.procesarPago()){
            reserva.setEstado(EstadoReserva.CONFIRMADA);
            reserva.setEstadoPago(EstadoPago.APROBADO);

            Reserva resConf = reservaRepo.save(reserva);
            publisher.publishEvent(new ReservaConfirmadaEvent(resConf));
            return ReservaMapper.mapToDto(resConf);
        }else{
            Reserva resConf = reservaRepo.save(reserva);
            return ReservaMapper.mapToDto(resConf);
        }

    }

    @Override
    @Transactional
    public ReservaResponseDto findReserva(long id) {

        Reserva reserva = reservaRepo.findByIdWithAssociacion(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"+id));
        return ReservaMapper.mapToDto(reserva);

    }

    @Override
    @Transactional
    public List<ReservaResponseDto> findAllReservas() {

        return reservaRepo.findAllWithAssociation()
                .stream()
                .map(ReservaMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ReservaResponseDto> findAllReservasByUsuarioId(long id) {
        return reservaRepo.findByUsuarioIdWithAssociacion(id)
                .stream()
                .map(ReservaMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReserva(long id, Usuario usuario) {

        Reserva reserva = reservaRepo.findByIdWithAssociacion(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"+id));

        // SÍ tiene permiso si es ADMIN O si es el dueño de la reserva
        boolean tienePermiso = "ADMIN".equals(usuario.getRol()) || reserva.getUsuario().getId().equals(usuario.getId());

        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaRepo.save(reserva);

    }


}
