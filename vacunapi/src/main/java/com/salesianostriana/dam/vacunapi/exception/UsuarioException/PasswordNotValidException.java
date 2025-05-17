package com.salesianostriana.dam.vacunapi.exception.UsuarioException;

public class PasswordNotValidException extends RuntimeException{
    public PasswordNotValidException() {
        super("Las contraseñas no coinciden");
    }
}
