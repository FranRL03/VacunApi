package com.salesianostriana.dam.vacunapi.dto.administracion;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public record GetAdministracionDto(
        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        Long id,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String fecha,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String edadAlAdministrar,

        @JsonView({PacienteView.findByIdWithAllEntities.class})
        String vacuna,

        @JsonView({PacienteView.findByIdWithAllEntities.class})
        String tipoDosis,

        String nota,

        @JsonView({AdministracionView.findAll.class})
        GetPacienteFindAll paciente,

        @JsonView({AdministracionView.findAll.class, PacienteView.idPacienteAdministracion.class})
        GetCalendarioDto calendario


) {

    public static GetAdministracionDto of (Administracion a){

        return new GetAdministracionDto(
                a.getId(),
                a.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                edad(a),
                a.getMomento().getVacuna().getNombre(),
                a.getMomento().getTipoDosis(),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetCalendarioDto.of(a.getMomento())
        );
    }

    public static GetAdministracionDto find (Administracion a){

        return new GetAdministracionDto(
                a.getId(),
                a.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                edad(a),
                a.getMomento().getVacuna().getNombre(),
                a.getMomento().getTipoDosis(),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetCalendarioDto.of(a.getMomento())
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
