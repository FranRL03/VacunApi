package com.salesianostriana.dam.vacunapi.dto.citas;

import com.salesianostriana.dam.vacunapi.modelo.Appointment;
import com.salesianostriana.dam.vacunapi.modelo.AppointmentStatus;

import java.time.format.DateTimeFormatter;

public record CitasFindAllDto(

        String id,
        String dia,
        String hora,
        String motivo,
        String notas,
        String sala,
        AppointmentStatus estado,
        String paciente,
        String medico
) {

    public static CitasFindAllDto of (Appointment c){

        return new CitasFindAllDto(
                c.getId().toString(),
                c.getFecha_inicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                c.getFecha_fin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                c.getMotivo(),
                c.getNotas(),
                c.getSala(),
                c.getEstado(),
                c.getPaciente().getName(),
                c.getMedico().getName()
        );

    }
}
