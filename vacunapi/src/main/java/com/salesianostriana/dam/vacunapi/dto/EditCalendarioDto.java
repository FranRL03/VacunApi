package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record EditCalendarioDto(

        int edad,

        String tipoDosis,

        String recomendaciones,

        String discriminante,

        List<Long> vacuna
) {

}
