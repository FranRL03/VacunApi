package com.salesianostriana.dam.vacunapi.dto.paciente;

import java.time.LocalDate;

public record EditPacienteDto(

        String nombre,

        String apellidos,

        String telefonoContacto,

        LocalDate fechaNacimiento,

        String notas
) {
}
