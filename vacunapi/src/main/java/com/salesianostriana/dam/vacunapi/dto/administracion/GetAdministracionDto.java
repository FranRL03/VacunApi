package com.salesianostriana.dam.vacunapi.dto.administracion;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView.*;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaIdNombreDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public record GetAdministracionDto(
        @JsonView({findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String id,

        @JsonView({findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String fecha,

        @JsonView({findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String edadAlAdministrar,

        @JsonView({findByIdWithAllEntities.class})
        String vacuna,

        @JsonView({findByIdWithAllEntities.class})
        String tipoDosis,

        @JsonView({AdministracionView.findAll.class})
        String nota,

        @JsonView({AdministracionView.findAll.class})
        GetPacienteFindAll paciente,

        @JsonView({idPacienteAdministracion.class})
        GetCalendarioDto calendario,

        @JsonView({AdministracionView.findAll.class})
        GetVacunaIdNombreDto vacunaInfo

) {

    public static GetAdministracionDto of (Administracion a){

        return new GetAdministracionDto(
                a.getId().toString(),
                a.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                edad(a),
                a.getMomento().getVacuna().getNombre(),
                a.getMomento().getTipoDosis(),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetCalendarioDto.of(a.getMomento()),
                GetVacunaIdNombreDto.of(a.getMomento().getVacuna())
        );
    }

    public static GetAdministracionDto find (Administracion a){

        return new GetAdministracionDto(
                a.getId().toString(),
                a.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                edad(a),
                a.getMomento().getVacuna().getNombre(),
                a.getMomento().getTipoDosis(),
                a.getNotas(),
                GetPacienteFindAll.of(a.getPaciente()),
                GetCalendarioDto.of(a.getMomento()),
                GetVacunaIdNombreDto.of(a.getMomento().getVacuna())
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
