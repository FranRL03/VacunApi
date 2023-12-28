package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record GetCalendarioFindAllDto(

        Long id,
        String edad,
        String tipoDosis,
        String Recomendaciones,
        String discriminante,
        String nombreVacuna
) {

    public static GetCalendarioFindAllDto of (Calendario c){

        return new GetCalendarioFindAllDto(
                c.getId(),
                c.getEdad() == 1 ? c.getEdad() + " mes" : c.getEdad() + " meses",
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna().getNombre()
        );

    }
}
