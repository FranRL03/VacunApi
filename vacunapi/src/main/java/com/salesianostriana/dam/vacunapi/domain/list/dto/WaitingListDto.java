package com.salesianostriana.dam.vacunapi.domain.list.dto;

import com.salesianostriana.dam.vacunapi.domain.list.model.StatusList;

import java.lang.String;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record WaitingListDto(
        String id,
        String patientName,
        String doctorName,
        String preferredDate,
        String status,
        String registerDate) {
}
