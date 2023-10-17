package com.salesianostriana.dam.vacunapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import static com.salesianostriana.dam.vacunapi.View.VacunaView.*;

import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetVacunaDto(

        @JsonView({VacunaList.class})
        Long id,

        @JsonView({VacunaList.class})
        String nombre,

        @JsonView({VacunaDetails.class})
        String descripcionEnfermedad,

        @JsonView({VacunaDetails.class})
        List<GetCalendarioDeVacunaDto> calendario
) {

    //@JsonView(VacunaList.class)
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

    //@JsonView(VacunaDetails.class)
    public static GetVacunaDto find (Vacuna v2){
        
        return new GetVacunaDto(
                v2.getId(),
                v2.getNombre(),
                v2.getDescripcionEnfermedad(),
                v2.getMomentos()
                        .stream()
                        .map(GetCalendarioDeVacunaDto::of)
                        .toList()
        );
    }

}
