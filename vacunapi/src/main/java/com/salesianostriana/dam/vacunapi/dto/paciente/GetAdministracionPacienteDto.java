package com.salesianostriana.dam.vacunapi.dto.paciente;

import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import org.apache.logging.log4j.message.LocalizedMessage;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetAdministracionPacienteDto(

        Long id,

        LocalDate fecha,

        int edadAlAdministrar,

        String nota
) {

    public static GetAdministracionPacienteDto of (Administracion a){

        return new GetAdministracionPacienteDto(
                a.getId(),
                a.getFecha(),
                a.getEdadAlAdministrar(),
                a.getNotas()
        );
    }
}
