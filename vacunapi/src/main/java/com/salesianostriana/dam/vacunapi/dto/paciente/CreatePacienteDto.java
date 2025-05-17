package com.salesianostriana.dam.vacunapi.dto.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

public record CreatePacienteDto(
//        @NotBlank(message = "{createClient.username}")
        String username,
        String password,
        String verifyPassword,
        String nombre,
        String apellidos,
        String telefono,
        String dni,
        String direccion,
        LocalDate fechaNacimiento,

//        @Email(message = "{loggedUser.email}")
        String email
) {
}
