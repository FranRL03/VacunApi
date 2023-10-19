package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record VacunaDetailsDto(

        GetVacunaDto vacuna
) {

    public static VacunaDetailsDto of (Calendario c){

        return new VacunaDetailsDto(

                GetVacunaDto.of(c.getVacuna())

        );
    }
}
