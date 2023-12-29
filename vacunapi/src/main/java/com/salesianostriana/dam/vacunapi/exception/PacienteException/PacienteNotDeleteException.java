package com.salesianostriana.dam.vacunapi.exception.PacienteException;

public class PacienteNotDeleteException extends RuntimeException{

    public PacienteNotDeleteException() {
        super ("El paciente no se puede borrar");
    }
}
