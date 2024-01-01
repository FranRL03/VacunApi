package com.salesianostriana.dam.vacunapi.exception.AdministracionException;

public class EmptyAdministracionListException extends RuntimeException{

    public EmptyAdministracionListException(){
        super("La lista de administraciones está vacía");
    }
}
