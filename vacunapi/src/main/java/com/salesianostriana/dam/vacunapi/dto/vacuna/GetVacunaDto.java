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

        @JsonView({VacunaList.class, CalendarioView.VacunaCalendario.class, CalendarioEdit.class,
                AdministracionView.findAll.class, AdministracionView.create.class})
        String id,

        @JsonView({VacunaList.class, PacienteView.findByIdWithAllEntities.class,
                CalendarioEdit.class, AdministracionView.findAll.class,
                PacienteView.idPacienteAdministracion.class, CalendarioView.VacunaCalendario.class, AdministracionView.create.class})
        String nombre,

        @JsonView({CalendarioView.VacunaCalendario.class,
                CalendarioEdit.class, AdministracionView.findById.class, VacunaList.class})
        String descripcion,

        @JsonView({AdministracionView.findById.class})
        List<GetCalendarioDeVacunaDto> momentos
) {

    public static GetVacunaDto of (Vacuna v){

        return new GetVacunaDto(
                v.getId().toString(),
                v.getNombre(),
                v.getDescripcion(),
                v.getMomentos()
                        .stream()
                        .map(GetCalendarioDeVacunaDto::of)
                        .toList()
        );
    }

}
