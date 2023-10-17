package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetCalendarioDto(

        Long id,

        int edad,

        String tipoDosis,

        String recomendaciones,

        String discriminante,

        Long idVacuna
) {


    public static GetCalendarioDto of (Calendario c){

        return new GetCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna()
        );
    }
}
