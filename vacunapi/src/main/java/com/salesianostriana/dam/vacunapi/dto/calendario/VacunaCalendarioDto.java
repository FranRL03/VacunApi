package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaPruebaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record VacunaCalendarioDto(

//        @JsonView({CalendarioView.findById.class})
        Long id,

//        @JsonView({CalendarioView.findById.class})
        String edad,

//        @JsonView({CalendarioView.findById.class})
        String tipoDosis,

//        @JsonView({CalendarioView.findById.class})
        String recomendaciones,

//        @JsonView({CalendarioView.findById.class})
        String discriminante,

//        @JsonView({CalendarioView.findById.class})
        GetVacunaPruebaDto vacuna
) {

    public static VacunaCalendarioDto of (Calendario c, int cantidadMomentos){

        return new VacunaCalendarioDto(
                c.getId(),
                c.getEdad() == 1 ? c.getEdad() + " mes" : c.getEdad() + " meses",
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                GetVacunaPruebaDto.of(c.getVacuna(), cantidadMomentos)

        );
    }
}
