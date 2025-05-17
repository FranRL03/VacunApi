package com.salesianostriana.dam.vacunapi.dto.usuario;


import com.salesianostriana.dam.vacunapi.modelo.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public record GetUserDetailDto(String id, String username, String email, Set<String> roles) {

    public static GetUserDetailDto of(Usuario u){
        return new GetUserDetailDto(
                u.getId().toString(),
                u.getUsername(),
                u.getEmail(),
                u.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet())

        );
    }
}
