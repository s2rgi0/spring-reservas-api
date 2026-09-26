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

    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.espacio WHERE r.usuario.id = :usuarioId")
    List<Reserva> findByUsuarioIdWithAssociacion(@Param("usuarioId") Long id);

    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario JOIN FETCH r.espacio WHERE r.id = :id")
    Optional<Reserva> findByIdWithAssociacion(@Param("id") Long id);

    List<Reserva> findByEspacioId(Long id);

    @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
            "WHERE r.espacio.id = :espacioId " +
            "AND r.fechaInicio < :fechaFin " +
            "AND r.fechaFin > :fechaInicio " +
            "AND r.estado IN (:estadosIgnorados)")
    boolean verificarSiEspacioEstaOcupado(
            @Param("espacioId") Long espacioId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("estadosIgnorados") List<EstadoReserva> estadosIgnorados
    );

}
