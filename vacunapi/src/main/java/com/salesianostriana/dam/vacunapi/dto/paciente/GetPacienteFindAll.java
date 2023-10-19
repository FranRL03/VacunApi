package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record GetPacienteFindAll(

        Long id,

        String nombre,

        String apellidos,

        int edad,

        int cantidadVacuna
) {

    public static GetPacienteFindAll of(Paciente p) {

        LocalDate fechaNaciemiento = p.getFechaNacimiento();

        int edad = edadPacienteYears(fechaNaciemiento);

            return new GetPacienteFindAll(
                    p.getId(),
                    p.getNombre(),
                    p.getApellidos(),
                    edad,
                    p.getVacunasAdministradas().size()
            );
        }

        /*
        ANOTACIÓN: EL MÉTODO TE MUESTRA LA EDAD EN MESES O EN AÑO PERO NO HE PODIDO
               CONSEGUIR CONCATENARLO PARA QUE APAREZCA DETRÁS DE LA EDAD "AÑOS" O "MESES"
         */

    public static int edadPacienteYears(LocalDate fechaNacimiento) {

        LocalDate fechaActual = LocalDate.now();
        int meses = (int) fechaNacimiento.until(fechaActual, ChronoUnit.MONTHS);

        if (meses < 24) {
            return meses;
        } else {
            return (int) fechaNacimiento.until(fechaActual, ChronoUnit.YEARS);
        }
    }
}
