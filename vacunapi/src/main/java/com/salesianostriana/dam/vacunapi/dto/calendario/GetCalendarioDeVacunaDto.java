package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.View.VacunaView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record GetCalendarioDeVacunaDto(

        @JsonView({AdministracionView.findById.class, VacunaView.VacunaDetails.class})
        Long id,

        @JsonView({AdministracionView.findById.class, PacienteView.idPacienteAdministracion.class, VacunaView.VacunaDetails.class})
        String tipoDosis,

        @JsonView({AdministracionView.findById.class, VacunaView.VacunaDetails.class})
        String recomendaciones,

        @JsonView({AdministracionView.findById.class, VacunaView.VacunaDetails.class})
        String discriminante
) {

    public static GetCalendarioDeVacunaDto of (Calendario c){

        return  new GetCalendarioDeVacunaDto(
                c.getId(),
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante()
        );
    }

}
