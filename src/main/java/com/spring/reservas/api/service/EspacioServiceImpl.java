package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.EspacioRequestDto;
import com.spring.reservas.api.dto.EspacioResponseDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.mapper.EspacioMapper;
import com.spring.reservas.api.repository.EspacioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspacioServiceImpl implements EspacioService {

    private final EspacioRepository repo;

    public EspacioServiceImpl(EspacioRepository repo) {
        this.repo = repo;
    }

    @Override
    public EspacioResponseDto createEspacio(EspacioRequestDto espacioRequestDto) {

        Espacio espacio = EspacioMapper.mapToJpa(espacioRequestDto);
        Espacio nuevoEspacio = repo.save(espacio);
        return EspacioMapper.mapToDto(nuevoEspacio);
    }

    @Override
    public EspacioResponseDto findById(long id) {
        return null;
    }

    @Override
    public List<EspacioResponseDto> findAll() {

        return repo.findAll()
                .stream()
                .map(EspacioMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EspacioResponseDto updateEspacio(EspacioRequestDto espacioRequestDto) {
        return null;
    }

    @Override
    public void deleteEspacio(long id) {

    }
}
