package com.salesianostriana.dam.vacunapi.exception.PacienteException;

public class PacienteNotFoundExcepcion extends RuntimeException{

    public PacienteNotFoundExcepcion(){
        super("Paciente not found");
    }
}
