package com.salesianostriana.dam.vacunapi.domain.list.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateWaitingListDto(
        UUID doctorId,
        LocalDate preferredDate
) {
}
