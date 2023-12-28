package com.salesianostriana.dam.vacunapi.exception.VacunaException;

public class VacunaNotDeleteException extends RuntimeException{

    public VacunaNotDeleteException () {
        super ("La vacuna no se puede borrar");
    }
}
