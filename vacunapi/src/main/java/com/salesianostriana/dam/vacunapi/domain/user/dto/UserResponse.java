package com.salesianostriana.dam.vacunapi.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserResponse {

    protected String id;
    protected String username;
    protected String rol;
    //protected String password;
    protected String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;


    public static UserResponse fromUser(User user) {

        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                //.password(user.getPassword())
                .rol(user.getRol().toString())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
