package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.fasterxml.jackson.annotation.JsonView;
import static com.salesianostriana.dam.vacunapi.View.VacunaView.*;

import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDeVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetVacunaDto(

        @JsonView({VacunaList.class, CalendarioView.VacunaCalendario.class, CalendarioEdit.class, AdministracionView.findAll.class})
        Long id,

        @JsonView({VacunaList.class, CalendarioView.CalendarioWithNameVacuna.class, PacienteView.findByIdWithAllEntities.class,
                CalendarioEdit.class, AdministracionView.findAll.class})
        String nombre,

        @JsonView({VacunaDetails.class, CalendarioView.VacunaCalendario.class,
                CalendarioEdit.class, AdministracionView.findById.class})
        String descripcionEnfermedad,

        @JsonView({VacunaDetails.class, AdministracionView.findById.class})
        List<GetCalendarioDeVacunaDto> momentos
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
