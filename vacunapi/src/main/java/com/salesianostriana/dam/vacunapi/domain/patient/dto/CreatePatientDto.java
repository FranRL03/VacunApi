package com.salesianostriana.dam.vacunapi.domain.patient.dto;

import com.salesianostriana.dam.vacunapi.domain.user.dto.CreateUserDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreatePatientDto(

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
        String phone,

        @Past(message = "{date.past}")
        LocalDate birthday,

        @NotBlank(message = "{create.null}")
        String dni,
        String address
) implements CreateUserDto {
}
