package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record EditCalendarioDto(

        int edad,

        String tipoDosis,

        String recomendaciones,

        String discriminante,

        Vacuna vacuna
) {

}
