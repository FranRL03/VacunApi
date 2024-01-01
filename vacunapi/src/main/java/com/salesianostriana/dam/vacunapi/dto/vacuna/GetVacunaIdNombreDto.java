package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDeVacunaDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioFindAllDto;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetVacunaIdNombreDto(
        @JsonView({AdministracionView.findAll.class})
        Long id,

        @JsonView({AdministracionView.findAll.class})
        String nombre,

        @JsonView({AdministracionView.findById.class})
        String descripcion,
        @JsonView({AdministracionView.findById.class})
        List<GetCalendarioFindAllDto> momentos
) {

    public static GetVacunaIdNombreDto of(Vacuna v){
        return new GetVacunaIdNombreDto(
                v.getId(),
                v.getNombre(),
                v.getDescripcionEnfermedad(),
                v.getMomentos()
                        .stream()
                        .map(GetCalendarioFindAllDto::of)
                        .toList()
        );
    }
}
