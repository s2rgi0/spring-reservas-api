package com.spring.reservas.api.entity;

import com.spring.reservas.api.enums.EstadoPago;
import com.spring.reservas.api.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false)
    private EstadoReserva estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estadoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espacio_id")
    private Espacio espacio;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

}
