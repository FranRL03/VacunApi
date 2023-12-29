package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record GetPacienteFindAll(

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        Long id,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String nombre,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        String apellidos,

        String edad,

        @JsonView({PacienteView.findByIdWithAllEntities.class, AdministracionView.findAll.class})
        int cantidadVacuna
) {

    public static GetPacienteFindAll of(Paciente p) {

        String edad = edad(p);

            return new GetPacienteFindAll(
                    p.getId(),
                    p.getNombre(),
                    p.getApellidos(),
                    edad,
                    p.getVacunasAdministradas().size()
            );
        }

    public static String edad (Paciente p){
        LocalDate fechaActual = LocalDate.now();
        int meses = (int) p.getFechaNacimiento().until(fechaActual, ChronoUnit.MONTHS);

        if (meses < 24) {
            return meses + " meses";
        } else {
            String años = String.valueOf(p.getFechaNacimiento().until(fechaActual, ChronoUnit.YEARS));
            return años + " años";
        }
    }

}
