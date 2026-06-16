package com.salesianostriana.dam.vacunapi.security.jwt.refresh;


import com.salesianostriana.dam.vacunapi.security.errorhandling.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg) {
        super(msg);
    }
}
