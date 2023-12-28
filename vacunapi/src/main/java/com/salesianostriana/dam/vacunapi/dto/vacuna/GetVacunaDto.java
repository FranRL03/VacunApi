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
                AdministracionView.findAll.class, vacunaCreate.class})
        Long id,

        @JsonView({VacunaList.class, PacienteView.findByIdWithAllEntities.class,
                CalendarioEdit.class, AdministracionView.findAll.class,
                PacienteView.idPacienteAdministracion.class, vacunaCreate.class, CalendarioView.VacunaCalendario.class})
        String nombre,

        @JsonView({VacunaDetails.class, CalendarioView.VacunaCalendario.class,
                CalendarioEdit.class, AdministracionView.findById.class, vacunaCreate.class, VacunaList.class})
        String descripcionEnfermedad,

        @JsonView({VacunaDetails.class, AdministracionView.findById.class})
        List<GetCalendarioDeVacunaDto> momentos
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
