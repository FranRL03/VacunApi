package com.salesianostriana.dam.vacunapi.domain.user.dto;

public record EditLoggedUserDto(
        String nombre,
        String apellidos,
        String telefono,
        String direccion,
        String username
) {
}
