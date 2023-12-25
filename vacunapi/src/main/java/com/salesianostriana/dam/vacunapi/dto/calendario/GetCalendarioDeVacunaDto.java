package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.View.VacunaView;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;

public record GetCalendarioDeVacunaDto(

        @JsonView({AdministracionView.findById.class, VacunaView.VacunaDetails.class})
        Long id,

        @JsonView({VacunaView.VacunaDetails.class})
        String edad,

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
                c.getEdad() == 1 ? c.getEdad() + " mes" : c.getEdad() + " meses",
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante()
        );
    }

}
