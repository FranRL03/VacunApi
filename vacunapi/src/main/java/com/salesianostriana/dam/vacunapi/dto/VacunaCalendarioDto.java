package com.salesianostriana.dam.vacunapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record VacunaCalendarioDto(

        @JsonView(CalendarioView.VacunaCalendario.class)
        Long id,

        @JsonView(CalendarioView.VacunaCalendario.class)
        String nombre,

        @JsonView(CalendarioView.VacunaCalendario.class)
        String descripcionEnfermedad
) {

    public static VacunaCalendarioDto of (Vacuna v){

        return new VacunaCalendarioDto(
                v.getId(),
                v.getNombre(),
                v.getDescripcionEnfermedad()
        );
    }
}
