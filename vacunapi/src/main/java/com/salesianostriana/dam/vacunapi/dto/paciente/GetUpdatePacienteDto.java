package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record GetUpdatePacienteDto (
        String nombre,

        String apellidos,

        String telefonoContacto,

        String fechaNacimiento,

        String notas
){

    public static GetUpdatePacienteDto of(Paciente p){
        return new GetUpdatePacienteDto(
                p.getNombre(),
                p.getApellidos(),
                p.getTelefonoContacto(),
                p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                p.getNotas()
        );
    }
}
