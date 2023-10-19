package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record GetPacienteDto(

        @JsonView({PacienteView.informacionPaciente.class, PacienteView.findByIdWithAllEntities.class})
        Long id,

        @JsonView({PacienteView.informacionPaciente.class, PacienteView.findByIdWithAllEntities.class})
        String nombre,

        @JsonView({PacienteView.informacionPaciente.class, PacienteView.findByIdWithAllEntities.class})
        String apellidos,

        @JsonView({PacienteView.informacionPaciente.class, PacienteView.findByIdWithAllEntities.class})
        String telefonoContacto,

        @JsonView({PacienteView.informacionPaciente.class, PacienteView.findByIdWithAllEntities.class})
        LocalDate fechaNacimiento,

        @JsonView({PacienteView.informacionPaciente.class, PacienteView.findByIdWithAllEntities.class})
        String notas,

        @JsonView({PacienteView.findByIdWithAllEntities.class})
        List<GetAdministracionDto> administracion

) {

    public static GetPacienteDto of (Paciente p){

        return new GetPacienteDto(
                p.getId(),
                p.getNombre(),
                p.getApellidos(),
                p.getTelefonoContacto(),
                p.getFechaNacimiento(),
                p.getNotas(),
                p.getVacunasAdministradas()
                        .stream()
                        .map(GetAdministracionDto::of)
                        .toList()
        );
    }

    public static GetPacienteDto find (Paciente p){

        return new GetPacienteDto(
                p.getId(),
                p.getNombre(),
                p.getApellidos(),
                p.getTelefonoContacto(),
                p.getFechaNacimiento(),
                p.getNotas(),
                p.getVacunasAdministradas()
                        .stream()
                        .map(GetAdministracionDto::of)
                        .toList()
        );
    }





}
