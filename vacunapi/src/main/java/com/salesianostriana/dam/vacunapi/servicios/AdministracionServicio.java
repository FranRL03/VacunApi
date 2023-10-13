package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.repositorios.AdministracionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministracionServicio {

    private final AdministracionRepositorio repositorio;

}
