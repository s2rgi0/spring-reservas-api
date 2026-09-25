package com.spring.reservas.api.event;

import com.spring.reservas.api.entity.Reserva;
import lombok.Getter;

@Getter
public class ReservaConfirmadaEvent {

    private final Reserva reserva;


    public ReservaConfirmadaEvent(Reserva reserva) {
        this.reserva = reserva;
    }


}
