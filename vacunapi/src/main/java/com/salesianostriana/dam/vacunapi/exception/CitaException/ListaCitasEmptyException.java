package com.salesianostriana.dam.vacunapi.exception.CitaException;

public class ListaCitasEmptyException extends RuntimeException{

    public ListaCitasEmptyException () {

        super ("No hay citas pendientes");
    }
}
