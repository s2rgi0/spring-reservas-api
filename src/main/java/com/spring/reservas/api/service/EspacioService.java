package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.EspacioRequestDto;
import com.spring.reservas.api.dto.EspacioResponseDto;

import java.util.List;

public interface EspacioService {


    EspacioResponseDto createEspacio(EspacioRequestDto espacioRequestDto);
    EspacioResponseDto findById(long id);
    List<EspacioResponseDto> findAll();
    EspacioResponseDto updateEspacio(Long id,EspacioRequestDto espacioRequestDto);
    void deleteEspacio(long id);

}
