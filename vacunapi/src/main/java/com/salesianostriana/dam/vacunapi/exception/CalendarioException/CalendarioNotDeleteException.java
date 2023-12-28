package com.salesianostriana.dam.vacunapi.exception.CalendarioException;

public class CalendarioNotDeleteException extends RuntimeException{

    public CalendarioNotDeleteException() {
        super ("El calendario no se puede borrar");
    }
}
