package com.salesianostriana.dam.vacunapi.dto.usuario;

import java.time.LocalDate;

public record EditLoggedUserDto(
        String nombre,
        String apellidos,
        String telefono,
        String direccion,
        String username
) {
}
