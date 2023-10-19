package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record VacunaCalendarioDto(

        Long id,
        int edad,
        String tipoDosis,
        String recomendaciones,
        String discriminante,
        int dosisTotales
) {

    public static VacunaCalendarioDto of (Calendario c, int dosisTotales){

        return new VacunaCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                dosisTotales

        );
    }
}
