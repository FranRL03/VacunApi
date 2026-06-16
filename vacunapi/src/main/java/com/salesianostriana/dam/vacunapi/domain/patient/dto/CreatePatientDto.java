package com.salesianostriana.dam.vacunapi.domain.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreatePatientDto(


        @NotBlank(message = "{create.null}")
        String name,

        String lastName,

        @NotBlank(message = "{create.null}")
        String phone,

        @Past(message = "{date.past}")
        LocalDate birthday,

        @NotBlank(message = "{create.null}")
        String dni,
        String address
) {
}
