package com.salesianostriana.dam.vacunapi.dto.administracion;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.EditPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;

public record GetAdministracionDto(
        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        Long id,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        LocalDate fecha,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        int edadAlAdministrar,

        String nota,

        @JsonView({AdministracionView.findAll.class})
        GetPacienteFindAll paciente,

        @JsonView({AdministracionView.findAll.class, PacienteView.idPacienteAdministracion.class})
        GetCalendarioDto calendario


) {

    public static GetAdministracionDto of (Administracion a){

        return new GetAdministracionDto(
                a.getId(),
                a.getFecha(),
                a.getEdadAlAdministrar(),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetCalendarioDto.of(a.getMomento())
        );
    }

    public static GetAdministracionDto find (Administracion a){

        return new GetAdministracionDto(
                a.getId(),
                a.getFecha(),
                a.getEdadAlAdministrar(),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetCalendarioDto.of(a.getMomento())
        );
    }



}
