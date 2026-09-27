package com.spring.reservas.api.integration;


import static org.junit.jupiter.api.Assertions.*;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.enums.TipoEspacio;
import com.spring.reservas.api.repository.EspacioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
public class EspacioRepositoryIntegrationTest {

    @Autowired
    private EspacioRepository espacioRepository;

    @Test
    void saveEspacioDB() {

        Espacio espacio = Espacio.builder()
                .nombre("Nuevo Espacio")
                .tipo(TipoEspacio.ESCRITORIO)
                .capacidad(2)
                .ubicacion("Zona 3")
                .tarifaHora(new BigDecimal("13.50"))
                .activo(true)
                .build();

        Espacio savedEspacio = espacioRepository.save(espacio);
        assertEquals("Nuevo Espacio", savedEspacio.getNombre());
    }


}
