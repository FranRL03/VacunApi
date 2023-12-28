package com.salesianostriana.dam.vacunapi.exception.CalendarioException;

public class EmptyCalendarioListException extends RuntimeException{
    public EmptyCalendarioListException (){
        super("No se encuentran calendarios en la lista");
    }
}
