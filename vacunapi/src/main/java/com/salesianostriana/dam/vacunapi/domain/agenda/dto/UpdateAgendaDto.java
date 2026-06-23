package com.salesianostriana.dam.vacunapi.domain.agenda.dto;

import java.time.LocalTime;

public record UpdateAgendaDto(

        Integer dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Integer duration,
        Boolean active
) {
}
