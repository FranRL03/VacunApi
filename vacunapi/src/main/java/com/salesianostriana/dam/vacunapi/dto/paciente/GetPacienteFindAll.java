package com.salesianostriana.dam.vacunapi.dto.paciente;

import java.time.LocalDate;

public record GetPacienteFindAll(

        Long id,

        String nombre,

        String apellidos,

        int edad,

        int cantidadVacuna
) {

    /*
    Hacer un método que te compare la fecha de nacimiento con la actual
    para calcular la edad del paciente
     */

    /*
    La cantidad de vacuna se saca con administración con un .lenght
     */
}
