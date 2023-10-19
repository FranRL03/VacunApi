package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record VacunaCalendarioDto(

        @JsonView({CalendarioView.findById.class})
        Long id,

        @JsonView({CalendarioView.findById.class})
        int edad,

        @JsonView({CalendarioView.findById.class})
        String tipoDosis,

        @JsonView({CalendarioView.findById.class})
        String recomendaciones,

        @JsonView({CalendarioView.findById.class})
        String discriminante,

        @JsonView({CalendarioView.findById.class})
        GetVacunaDto vacuna,

        @JsonView({CalendarioView.findById.class})
        int dosisTotales
) {

    public static VacunaCalendarioDto of (Calendario c, int dosisTotales){

        return new VacunaCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                GetVacunaDto.of(c.getVacuna()),
                dosisTotales

        );
    }
}
