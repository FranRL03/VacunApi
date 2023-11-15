package com.salesianostriana.dam.vacunapi.error.model;

public class VacunaNotFoundExcepcion extends RuntimeException{

    public VacunaNotFoundExcepcion (Long id){
        super("Vacuna with id: "+id+ " not found");
    }
}
