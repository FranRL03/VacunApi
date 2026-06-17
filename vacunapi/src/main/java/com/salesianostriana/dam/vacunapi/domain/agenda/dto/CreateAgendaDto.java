package com.salesianostriana.dam.vacunapi.domain.agenda.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CreateAgendaDto(

        @NotNull(message = "{create.null}")
        @Min(value = 1)
        @Max(value = 5)
        int dayOfWeek,

        @NotNull(message = "{create.null}")
        LocalTime startTime,

        @NotNull(message = "{create.null}")
        LocalTime endTime
) {
}
