package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record VacunaCalendarioDto(

        Long id,
        int edad,
        String tipoDosis,
        String recomendaciones,
        String discriminante,
        String vacuna
) {

    public static VacunaCalendarioDto of (Calendario c){

        return new VacunaCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna().toString()
        );
    }

}
