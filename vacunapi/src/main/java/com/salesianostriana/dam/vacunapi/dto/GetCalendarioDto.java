package com.salesianostriana.dam.vacunapi.dto;

import java.util.List;

public record GetCalendarioDto(

        Long id,

        int edad,

        String tipoDosis,

        String recomendaciones,

        String discriminante
) {


}
