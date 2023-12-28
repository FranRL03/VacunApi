package com.salesianostriana.dam.vacunapi.exception.VacunaException;

public class VacunaNotFoundExcepcion extends RuntimeException{

    public VacunaNotFoundExcepcion (){
        super("Vacuna not found");
    }
}
