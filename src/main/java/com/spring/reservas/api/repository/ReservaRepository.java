package com.spring.reservas.api.repository;

import com.spring.reservas.api.entity.Reserva;
import com.spring.reservas.api.enums.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {


    List<Reserva> findByEstado(EstadoReserva estadoReserva);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.espacio")
    List<Reserva> findAllWithAssociation();

    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.espacio WHERE usuario.id = :id")
    List<Reserva> findByUsuarioIdWithAssociacion(@Param("usuarioId") Long id);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.espacio WHERE r.id = :id")
    Optional<Reserva> findByIdWithAssociacion(@Param("id") Long id);

    List<Reserva> findByEspacioId(Long id);

    boolean existsByFechaInicio(Long id, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
