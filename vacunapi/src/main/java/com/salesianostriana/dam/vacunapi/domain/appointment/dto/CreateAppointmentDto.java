package com.salesianostriana.dam.vacunapi.domain.appointment.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateAppointmentDto(

        UUID doctorId,
        LocalDate date,
        LocalTime hour,
        String reason
) {
}
