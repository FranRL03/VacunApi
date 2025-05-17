package com.salesianostriana.dam.vacunapi.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.vacunapi.modelo.Usuario;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUserResponse extends UserResponse {

    private String token;
    private String refreshToken;

    public JwtUserResponse(UserResponse userResponse) {
        id = userResponse.getId();
        username = userResponse.getUsername();
        roles=userResponse.getRoles();
        createdAt = userResponse.getCreatedAt();
    }

    public static JwtUserResponse of (Usuario user, String token) {
        JwtUserResponse result = new JwtUserResponse(UserResponse.fromUser(user));
        result.setToken(token);
        return result;

    }

}

