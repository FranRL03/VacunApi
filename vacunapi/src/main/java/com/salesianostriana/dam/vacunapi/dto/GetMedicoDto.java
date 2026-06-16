package com.salesianostriana.dam.vacunapi.dto;

import com.salesianostriana.dam.vacunapi.modelo.Doctor;

public record GetMedicoDto(

        String id,
        String nombre,
        String apellidos,
        String especialidad,
        String telefeono
) {

    public static GetMedicoDto of (Doctor m){

        return new GetMedicoDto(
                m.getId().toString(),
                m.getNombre(),
                m.getApellidos(),
                m.getTelefono(),
                m.getEspecialidad()
        );
    }
}
