package com.salesianostriana.dam.vacunapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.Arrays;
import java.util.List;

public record GetCalendarioDto(

        @JsonView(CalendarioView.VacunaCalendario.class)
        Long id,

        @JsonView(CalendarioView.VacunaCalendario.class)
        int edad,

        @JsonView(CalendarioView.VacunaCalendario.class)
        String tipoDosis,

        @JsonView(CalendarioView.VacunaCalendario.class)
        String recomendaciones,

        @JsonView(CalendarioView.VacunaCalendario.class)
        String discriminante,

        @JsonView(CalendarioView.VacunaCalendario.class)
        Long idVacuna

//       String nombreVacuna,
//
//        String descripcionEnfermedad

) {


    public static GetCalendarioDto of (Calendario c){

        Vacuna v = c.getVacuna();

        return new GetCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna().getId()
//                v.getNombre(),
//                v.getDescripcionEnfermedad()
        );
    }

}
