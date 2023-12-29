package com.salesianostriana.dam.vacunapi.dto.administracion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;

import com.salesianostriana.dam.vacunapi.modelo.Paciente;


import java.time.LocalDate;

public record EditAdministracionDto(

        Long id,
        @JsonView({ AdministracionView.create.class})
        LocalDate fecha,
        @JsonView({ AdministracionView.create.class})
        int edadAlAdministrar,
        @JsonView({ AdministracionView.create.class})
        String notas,
        @JsonView({ AdministracionView.create.class})
        Long idPaciente,
        @JsonView({ AdministracionView.create.class})
        Long idCalendario

) {

}


