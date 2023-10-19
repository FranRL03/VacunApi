package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.View.VacunaView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record GetCalendarioDto(

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        Long id,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        int edad,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        String tipoDosis,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        String recomendaciones,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        String discriminante,

        @JsonView({CalendarioView.VacunaCalendario.class})
        Long idVacuna,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        String nombre

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

        );
    }

    


}
