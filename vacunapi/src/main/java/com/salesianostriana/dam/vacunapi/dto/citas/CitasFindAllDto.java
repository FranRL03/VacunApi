package com.salesianostriana.dam.vacunapi.dto.citas;

import com.salesianostriana.dam.vacunapi.modelo.Cita;
import com.salesianostriana.dam.vacunapi.modelo.EstadoCitas;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

public record CitasFindAllDto(

        String id,
        String dia,
        String hora,
        String motivo,
        String notas,
        String sala,
        EstadoCitas estado,
        String idPaciente,
        String idMedico
) {

    public static CitasFindAllDto of (Cita c){

        return new CitasFindAllDto(
                c.getId().toString(),
                c.getDia().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                c.getHora().format(DateTimeFormatter.ofPattern("HH:mm")),
                c.getMotivo(),
                c.getNotas(),
                c.getSala(),
                c.getEstado(),
                c.getPaciente().getId().toString(),
                c.getMedico().getId().toString()
        );

    }
}
