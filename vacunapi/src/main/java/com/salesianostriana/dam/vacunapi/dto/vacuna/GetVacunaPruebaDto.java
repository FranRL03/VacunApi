package com.salesianostriana.dam.vacunapi.dto.vacuna;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.View.VacunaView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDeVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.util.List;

public record GetVacunaPruebaDto (
//        @JsonView({VacunaView.VacunaList.class, CalendarioView.VacunaCalendario.class, VacunaView.CalendarioEdit.class,
//                AdministracionView.findAll.class, VacunaView.vacunaCreate.class})
        Long id,

//        @JsonView({VacunaView.VacunaList.class, PacienteView.findByIdWithAllEntities.class,
//                VacunaView.CalendarioEdit.class, AdministracionView.findAll.class,
//                PacienteView.idPacienteAdministracion.class, VacunaView.vacunaCreate.class, CalendarioView.VacunaCalendario.class})
        String nombre,

//        @JsonView({VacunaView.VacunaDetails.class, CalendarioView.VacunaCalendario.class,
//                VacunaView.CalendarioEdit.class, AdministracionView.findById.class, VacunaView.vacunaCreate.class, VacunaView.VacunaList.class})
        String descripcionEnfermedad,

//        @JsonView({CalendarioView.findById.class})
        int dosisTotales
){

    public static GetVacunaPruebaDto of(Vacuna v, int dosisTotales){

        return new GetVacunaPruebaDto(
                v.getId(),
                v.getNombre(),
                v.getDescripcionEnfermedad(),
                dosisTotales
        );
    }
}
