package com.salesianostriana.dam.vacunapi.servicios;


import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteServicio {

    private final PacienteRepositorio repositorio;

}
