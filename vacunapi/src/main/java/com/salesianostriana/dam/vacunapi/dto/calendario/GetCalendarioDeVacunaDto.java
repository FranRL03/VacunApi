package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record GetCalendarioDeVacunaDto(

        Long id,
        String tipoDosis,
        String recomendaciones,
        String discriminante
) {

    public static GetCalendarioDeVacunaDto of (Calendario c){

        return  new GetCalendarioDeVacunaDto(
                c.getId(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante()
        );
    }

}
