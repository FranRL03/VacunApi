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

        String id,
        String nombre,
        String descripcion,
        int dosisTotales
){

    public static GetVacunaPruebaDto of(Vacuna v, int dosisTotales){

        return new GetVacunaPruebaDto(
                v.getId().toString(),
                v.getNombre(),
                v.getDescripcion(),
                dosisTotales
        );
    }
}
