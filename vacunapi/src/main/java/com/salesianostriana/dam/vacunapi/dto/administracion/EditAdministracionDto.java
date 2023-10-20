package com.salesianostriana.dam.vacunapi.dto.administracion;

import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;

import com.salesianostriana.dam.vacunapi.modelo.Paciente;


import java.time.LocalDate;

public record EditAdministracionDto(

        Long id,

        LocalDate fecha,

        int edadAlAdministrar,

        String notas



) {

}


