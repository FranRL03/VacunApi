package com.salesianostriana.dam.vacunapi.domain.user.dto;


import com.salesianostriana.dam.vacunapi.domain.user.model.User;

public record GetUserDetailDto(String id, String username, String email, String roles) {

    public static GetUserDetailDto of(User u){
        return new GetUserDetailDto(
                u.getId().toString(),
                u.getUsername(),
                u.getEmail(),
                u.getRol().toString()

        );
    }
}
