package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDeVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record VacunaDetailsDto(

        Long id,

        String nombre,

        String descripcion,

        int dosisTotales,

        List<GetCalendarioDeVacunaDto> momentos

) {

    public static VacunaDetailsDto of(Vacuna v, int dosisTotales){

        return new VacunaDetailsDto(
                v.getId(),
                v.getNombre(),
                v.getDescripcion(),
                dosisTotales,
                v.getMomentos()
                        .stream()
                        .map(GetCalendarioDeVacunaDto::of)
                        .toList()
        );
    }

}
