package com.salesianostriana.dam.vacunapi.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
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

    private UserResponse user;
    private String accessToken;
    private String refreshToken;

    public static JwtUserResponse of(User u,
                                     String accessToken,
                                     String refreshToken) {

        return JwtUserResponse.builder()
                .user(UserResponse.fromUser(u))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}

