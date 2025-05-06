package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaPruebaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record VacunaCalendarioDto(

        String id,

        String edad,

        String tipoDosis,

        String recomendaciones,

        String discriminante,

        GetVacunaPruebaDto vacuna
) {

    public static VacunaCalendarioDto of (Calendario c, int cantidadMomentos){

        return new VacunaCalendarioDto(
                c.getId().toString(),
                c.getEdad() == 1 ? c.getEdad() + " mes" : c.getEdad() + " meses",
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                GetVacunaPruebaDto.of(c.getVacuna(), cantidadMomentos)

        );
    }
}
