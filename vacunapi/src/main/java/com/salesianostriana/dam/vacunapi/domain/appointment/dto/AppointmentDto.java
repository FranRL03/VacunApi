package com.salesianostriana.dam.vacunapi.domain.appointment.dto;

public record AppointmentDto(
        String id,
        String startDateTime,
        String endDateTime,
        String reason,
        String notes,
        String room,
        String status,
        String patientName,
        String doctorName
) {

    
}
