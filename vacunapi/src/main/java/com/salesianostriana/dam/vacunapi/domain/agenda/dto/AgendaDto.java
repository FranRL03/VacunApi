package com.salesianostriana.dam.vacunapi.domain.agenda.dto;

public record AgendaDto(

        String id,
        String dayOfWeek,
        String startTime,
        String endTime,
        int duration,
        boolean active,
        String doctorName
) {
}
