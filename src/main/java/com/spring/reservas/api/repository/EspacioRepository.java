package com.spring.reservas.api.repository;

import com.spring.reservas.api.dto.ReservaResponseDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {


}
