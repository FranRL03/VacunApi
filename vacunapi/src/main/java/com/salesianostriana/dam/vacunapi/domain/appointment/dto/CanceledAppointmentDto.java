package com.salesianostriana.dam.vacunapi.domain.appointment.dto;

import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.AppointmentStatus;

public record CanceledAppointmentDto(
       String cancellationReason
) {
}
