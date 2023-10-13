package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacunaServicio {

    private final VacunaRepositorio repositorio;

}
