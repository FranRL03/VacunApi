package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetVacunaDto(
        Long id,
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
