package com.salesianostriana.dam.vacunapi.exception.CalendarioException;

public class ErrorEditCalendarioException extends RuntimeException{
    public ErrorEditCalendarioException(){
        super("No es posible editar este calendario");
    }
}
