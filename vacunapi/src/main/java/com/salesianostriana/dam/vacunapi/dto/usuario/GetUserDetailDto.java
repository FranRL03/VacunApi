package com.salesianostriana.dam.vacunapi.dto.usuario;


import com.salesianostriana.dam.vacunapi.modelo.User;

import java.util.Set;
import java.util.stream.Collectors;

public record GetUserDetailDto(String id, String username, String email, String roles) {

    public static GetUserDetailDto of(User u){
        return new GetUserDetailDto(
                u.getId().toString(),
                u.getUsername(),
                u.getEmail(),
                u.getRole().toString()

        );
    }
}
