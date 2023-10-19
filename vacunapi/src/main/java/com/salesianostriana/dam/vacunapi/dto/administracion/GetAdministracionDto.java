package com.salesianostriana.dam.vacunapi.dto.administracion;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;

import java.time.LocalDate;

public record GetAdministracionDto(
        @JsonView({PacienteView.findByIdWithAllEntities.class})
        Long id,

        @JsonView({PacienteView.findByIdWithAllEntities.class})
        LocalDate fecha,

        @JsonView({PacienteView.findByIdWithAllEntities.class})
        int edadAlAdministrar,

        String nota,

        @JsonView({PacienteView.findByIdWithAllEntities.class})
        GetCalendarioDto getCalendario
) {

    public static GetAdministracionDto of (Administracion a){

        return new GetAdministracionDto(
                a.getId(),
                a.getFecha(),
                a.getEdadAlAdministrar(),
                a.getNotas(),
                GetCalendarioDto.of(a.getMomento())
        );
    }



}
