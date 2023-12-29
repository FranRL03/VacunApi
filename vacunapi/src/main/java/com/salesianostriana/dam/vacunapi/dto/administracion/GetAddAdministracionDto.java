package com.salesianostriana.dam.vacunapi.dto.administracion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public record GetAddAdministracionDto(
        @JsonView({ AdministracionView.create.class})
        Long id,
        @JsonView({ AdministracionView.create.class})
        String fecha,
        @JsonView({ AdministracionView.create.class})
        String edadAlAdministrar,
        @JsonView({ AdministracionView.create.class})
        String notas,
        @JsonView({ AdministracionView.create.class})
        GetPacienteFindAll paciente,
        @JsonView({ AdministracionView.create.class})
        GetVacunaDto vacuna
) {

    public static GetAddAdministracionDto of (Administracion a){
        return new GetAddAdministracionDto(
                a.getId(),
                a.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                edad(a),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetVacunaDto.of(a.getMomento().getVacuna())
        );
    }

    public static String edad (Administracion a){

        if (a.getEdadAlAdministrar() < 24) {
            return a.getEdadAlAdministrar() + " meses";
        } else {
            String años = String.valueOf(a.getEdadAlAdministrar());
            return años + " años";
        }
    }
}
