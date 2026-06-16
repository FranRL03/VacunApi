package com.salesianostriana.dam.vacunapi.domain.doctor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateDoctorDto(

        @NotBlank(message = "{create.null}")
        String username,

        @NotBlank(message = "{create.null}")
        @Email(message = "{email.valid}")
        String email,
        String password,

        @NotBlank(message = "{create.null}")
        String name,
        String lastName,

        @NotBlank(message = "{create.null}")
        String speciality,

        @NotBlank(message = "{create.null}")
        String phone
) {
}
