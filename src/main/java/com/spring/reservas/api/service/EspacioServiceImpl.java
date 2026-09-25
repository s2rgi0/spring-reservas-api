package com.spring.reservas.api.service;

import com.spring.reservas.api.dto.EspacioRequestDto;
import com.spring.reservas.api.dto.EspacioResponseDto;
import com.spring.reservas.api.entity.Espacio;
import com.spring.reservas.api.exception.EntityNotFoundException;
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
        Espacio espacio = repo.findById( id )
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado"+id));
        return EspacioMapper.mapToDto(espacio);
    }

    @Override
    public List<EspacioResponseDto> findAll() {

        return repo.findAll()
                .stream()
                .map(EspacioMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EspacioResponseDto updateEspacio(Long id,EspacioRequestDto espacioRequestDto) {
        Espacio espacio = repo.findById( id )
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado"+id));

        espacio.setNombre(espacioRequestDto.getNombre());
        espacio.setTipo(espacioRequestDto.getTipo());
        espacio.setCapacidad(espacioRequestDto.getCapacidad());
        espacio.setUbicacion(espacioRequestDto.getUbicacion());
        espacio.setTarifaHora(espacioRequestDto.getTarifaHora());

        return EspacioMapper.mapToDto(repo.save(espacio));
    }

    @Override
    public void deleteEspacio(long id) {

        Espacio espacio = repo.findById( id )
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado"+id));

        repo.delete(espacio);
    }
}
