package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;

public record GetPacienteFindAll(

        Long id,

        String nombre,

        String apellidos,

        int edad,

        int cantidadVacuna
) {

    public GetPacienteFindAll of (Paciente p){

        return new GetPacienteFindAll(
                p.getId(),
                p.getNombre(),
                p.getApellidos(),
                p.getFechaNacimiento(),
                p.getVacunasAdministradas().size()
        );
    }

    public int edadPaciente (LocalDate fechaNacimiento){

        
    }

    /*
    Hacer un método que te compare la fecha de nacimiento con la actual
    para calcular la edad del paciente
     */
}
