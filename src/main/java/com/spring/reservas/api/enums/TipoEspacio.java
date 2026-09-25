package com.spring.reservas.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoEspacio {
    SALA_REUNIONES,
    ESCRITORIO,
    CUBICULO,
    OFICINA;

    @JsonCreator
    public static TipoEspacio fromString(String value) {
        if (value == null) return null;
        try {
            return TipoEspacio.valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valores permitidos: SALA_REUNIONES, ESCRITORIO, CUBICULO, OFICINA");
        }
    }
}
