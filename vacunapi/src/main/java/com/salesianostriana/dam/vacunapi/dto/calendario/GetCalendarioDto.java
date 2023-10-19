package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.View.VacunaView;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record GetCalendarioDto(

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class})
        Long id,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        int edad,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class,
                PacienteView.findByIdWithAllEntities.class})
        String tipoDosis,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        String recomendaciones,

        @JsonView({CalendarioView.CalendarioWithNameVacuna.class, VacunaView.CalendarioEdit.class})
        String discriminante,

        @JsonView({VacunaView.CalendarioEdit .class, AdministracionView.findAll.class})
        GetVacunaDto vacuna
) {


    public static GetCalendarioDto of (Calendario c){

        Vacuna v = c.getVacuna();

        return new GetCalendarioDto(
                c.getId(),
                c.getEdad(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                GetVacunaDto.of(c.getVacuna())

        );
    }

    


}
