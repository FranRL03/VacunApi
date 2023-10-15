package com.salesianostriana.dam.vacunapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import static com.salesianostriana.dam.vacunapi.View.VacunaView.*;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetVacunaDto(

        @JsonView(VacunaList.class)
        Long id,

        @JsonView(VacunaList.class)
        String nombre,
        String descripcionEnfermedad,
        List<GetCalendarioDeVacunaDto> calendario
) {

    public static GetVacunaDto of (Vacuna v){

        return new GetVacunaDto(
                v.getId(),
                v.getNombre(),
                v.getDescripcionEnfermedad(),
                v.getMomentos()
                        .stream()
                        .map(GetCalendarioDeVacunaDto::of)
                        .toList()
        );
    }



}
