package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarioServicio {

    private final CalendarioRepositorio repositorio;

}
