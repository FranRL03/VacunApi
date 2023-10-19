package com.salesianostriana.dam.vacunapi.dto.paciente;

import java.time.LocalDate;

public record GetPacienteFindAll(

        Long id,

        String nombre,

        String apellidos,

        int edad,

        int cantidadVacuna
) {
    
}
