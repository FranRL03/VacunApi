package com.salesianostriana.dam.vacunapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.Arrays;
import java.util.List;

public record GetCalendarioDto(

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        Long id,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        int edad,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        String tipoDosis,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        String recomendaciones,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        String discriminante,

        @JsonView({CalendarioView.VacunaCalendario.class})
        Long idVacuna,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        String nombre

       // @JsonView({CalendarioView.VacunaCalendario.class})
        //String descripcionEnfermedad

) {


    public static GetCalendarioDto of (Calendario c){

        Vacuna v = c.getVacuna();

        return new GetCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna().getId(),
                c.getVacuna().getNombre()
                //c.getVacuna().getDescripcionEnfermedad()

        );
    }


}
