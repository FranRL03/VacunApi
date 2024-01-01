package com.salesianostriana.dam.vacunapi.exception.AdministracionException;

public class AdministrationNotFoundExcepcion extends RuntimeException{

    public AdministrationNotFoundExcepcion(){
        super("Administration not found");
    }
}
