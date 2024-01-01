package com.salesianostriana.dam.vacunapi.exception.AdministracionException;

public class AdministrationNotDeleteException extends RuntimeException{

    public AdministrationNotDeleteException() {
        super ("“No se puede borrar la información de administración de una vacuna a un paciente");
    }
}
