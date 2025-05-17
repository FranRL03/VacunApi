package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.PacienteView.*;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record GetPacienteDto(

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String id,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String nombre,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String apellidos,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String telefonoContacto,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String fechaNacimiento,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String dni,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String direccion,

        @JsonView({informacionPaciente.class, findByIdWithAllEntities.class})
        String notas,

        @JsonView({findByIdWithAllEntities.class})
        List<GetAdministracionDto> vacunasAdministradas

) {

    public static GetPacienteDto of (Paciente p){

        return new GetPacienteDto(
                p.getId().toString(),
                p.getNombre(),
                p.getApellidos(),
                p.getTelefonoContacto(),
                p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                p.getDni(),
                p.getDireccion(),
                p.getNotas(),
                p.getVacunasAdministradas()
                        .stream()
                        .map(GetAdministracionDto::of)
                        .toList()
        );
    }

    public static GetPacienteDto find (Paciente p){

        return new GetPacienteDto(
                p.getId().toString(),
                p.getNombre(),
                p.getApellidos(),
                p.getTelefonoContacto(),
                p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                p.getDni(),
                p.getDireccion(),
                p.getNotas(),
                p.getVacunasAdministradas()
                        .stream()
                        .map(GetAdministracionDto::of)
                        .toList()
        );
    }





}
