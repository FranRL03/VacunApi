package com.salesianostriana.dam.vacunapi.exception.PacienteException;

public class EmptyPacienteListException extends RuntimeException{

    public EmptyPacienteListException(){
        super("La lista de pacientes está vacía");
    }
}
