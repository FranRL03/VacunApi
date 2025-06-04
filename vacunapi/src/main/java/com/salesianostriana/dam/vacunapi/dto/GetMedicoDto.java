package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.modelo.Medico;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;

import java.time.format.DateTimeFormatter;

public record GetMedicoDto(

        String id,
        String nombre,
        String apellidos,
        String especialidad,
        String telefeono
) {

    public static GetMedicoDto of (Medico m){

        return new GetMedicoDto(
                m.getId().toString(),
                m.getNombre(),
                m.getApellidos(),
                m.getTelefono(),
                m.getEspecialidad()
        );
    }
}
