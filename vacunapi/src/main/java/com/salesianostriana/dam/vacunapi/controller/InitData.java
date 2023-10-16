package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@RequiredArgsConstructor
public class InitData {

    private final VacunaRepositorio vacunaRepositorio;
    private final CalendarioRepositorio calendarioRepositorio;

    @PostConstruct
    public void init(){

        Calendario c1 =
    }
}
