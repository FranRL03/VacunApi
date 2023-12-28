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

        Long id,

        @JsonView({VacunaView.CalendarioEdit.class})
        String edad,

        @JsonView({VacunaView.CalendarioEdit.class,
                PacienteView.findByIdWithAllEntities.class})
        String tipoDosis,

        @JsonView({VacunaView.CalendarioEdit.class})
        String recomendaciones,

        @JsonView({VacunaView.CalendarioEdit.class})
        String discriminante,

        @JsonView({VacunaView.CalendarioEdit .class, AdministracionView.findAll.class, PacienteView.idPacienteAdministracion.class})
        GetVacunaDto vacuna
) {


    public static GetCalendarioDto of (Calendario c){

        return new GetCalendarioDto(
                c.getId(),
                c.getEdad() == 1 ? c.getEdad() + " mes" : c.getEdad() + " meses",
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                GetVacunaDto.of(c.getVacuna())

        );
    }

    


}
