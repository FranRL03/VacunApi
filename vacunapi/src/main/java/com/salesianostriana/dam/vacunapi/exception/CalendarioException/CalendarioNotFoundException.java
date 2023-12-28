package com.salesianostriana.dam.vacunapi.exception.CalendarioException;

public class CalendarioNotFoundException extends RuntimeException{
    public CalendarioNotFoundException(){
        super("No se ha encontrado el calendario");
    }
}
