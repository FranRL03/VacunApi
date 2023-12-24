package com.salesianostriana.dam.vacunapi.exception;

public class EmptyVacunaListException extends RuntimeException{

    public EmptyVacunaListException(){
        super("La lista de vacunas está vacía");
    }
}
