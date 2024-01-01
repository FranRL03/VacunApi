package com.salesianostriana.dam.vacunapi.dto.calendario;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

public record GetCalendarioFindAllDto(

        @JsonView({AdministracionView.findById.class})
        Long id,
        @JsonView({AdministracionView.findById.class})
        String edad,
        @JsonView({AdministracionView.findById.class})
        String tipoDosis,
        @JsonView({AdministracionView.findById.class})
        String Recomendaciones,
        @JsonView({AdministracionView.findById.class})
        String discriminante,
        String nombreVacuna
) {

    public static GetCalendarioFindAllDto of (Calendario c){

        return new GetCalendarioFindAllDto(
                c.getId(),
                c.getEdad() == 1 ? c.getEdad() + " mes" : c.getEdad() + " meses",
                c.getTipoDosis(),
                c.getRecomendaciones(),
                c.getDiscriminante(),
                c.getVacuna().getNombre()
        );

    }
}
