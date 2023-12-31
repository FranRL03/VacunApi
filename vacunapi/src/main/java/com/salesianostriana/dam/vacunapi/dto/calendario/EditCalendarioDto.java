package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.View.VacunaView;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record EditCalendarioDto(

        @JsonView({CalendarioView.VacunaCalendario.class})
        Long id,

        @JsonView({CalendarioView.VacunaCalendario.class, VacunaView.CalendarioEdit.class})
        int edad,

        @JsonView({CalendarioView.VacunaCalendario.class, VacunaView.CalendarioEdit.class})
        String tipoDosis,

        @JsonView({CalendarioView.VacunaCalendario.class, VacunaView.CalendarioEdit.class})
        String recomendaciones,

        @JsonView({CalendarioView.VacunaCalendario.class, VacunaView.CalendarioEdit.class})
        String discriminante,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, CalendarioView.VacunaCalendario.class})
        GetVacunaDto vacuna


        ) {

    public static EditCalendarioDto of (Calendario c){

        Vacuna v = c.getVacuna();

        return new EditCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                GetVacunaDto.of(c.getVacuna())
        );
    }

}
