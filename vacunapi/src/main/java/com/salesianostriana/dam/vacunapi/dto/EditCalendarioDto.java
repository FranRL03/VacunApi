package com.salesianostriana.dam.vacunapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record EditCalendarioDto(

        @JsonView({CalendarioView.VacunaCalendario.class})
        Long id,

        @JsonView({CalendarioView.VacunaCalendario.class})
        int edad,

        @JsonView({CalendarioView.VacunaCalendario.class})
        String tipoDosis,

        @JsonView({CalendarioView.VacunaCalendario.class})
        String recomendaciones,

        @JsonView({CalendarioView.VacunaCalendario.class})
        String discriminante,

        @JsonView({CalendarioView.VacunaCalendario.class})
        Long idVacuna,

        @JsonView({CalendarioView.VacunaCalendario.class})
        String nombre,

       @JsonView({CalendarioView.VacunaCalendario.class})
        String descripcionEnfermedad


        ) {

    public static EditCalendarioDto of (Calendario c){

        Vacuna v = c.getVacuna();

        return new EditCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna().getId(),
                c.getVacuna().getNombre(),
                c.getVacuna().getDescripcionEnfermedad()
        );
    }

}
