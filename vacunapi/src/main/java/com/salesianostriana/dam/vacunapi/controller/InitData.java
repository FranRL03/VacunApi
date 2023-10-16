package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final VacunaRepositorio vacunaRepositorio;
    private final CalendarioRepositorio calendarioRepositorio;

    @PostConstruct
    public void init(){

        Calendario c1 = Calendario.builder()
                .edad(2)
                .tipoDosis("Segunda")
                .recomendaciones("Reposo durante el día")
                .discriminante("T")
                .build();
        Calendario c2 = Calendario.builder()
                .edad(6)
                .tipoDosis("Refuerzo")
                .recomendaciones("Ponerse frío")
                .discriminante("H")
                .build();

        calendarioRepositorio.saveAll(List.of(c1, c2));

        Vacuna v = Vacuna.builder()
                .nombre("Alergia")
                .descripcionEnfermedad("Alergia contra el polen y los ácaros")
                .momentos(List.of(c1, c2))
                .build();

        vacunaRepositorio.save(v);

    }
}
