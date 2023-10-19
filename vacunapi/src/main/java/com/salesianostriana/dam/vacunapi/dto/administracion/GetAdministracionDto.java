package com.salesianostriana.dam.vacunapi.dto.administracion;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;

import java.time.LocalDate;

public record GetAdministracionDto(
        Long id,

        LocalDate fecha,

        int edadAlAdministrar,

        String nota
) {

    public static GetAdministracionDto of (Administracion a){

        return new GetAdministracionDto(
                a.getId(),
                a.getFecha(),
                a.getEdadAlAdministrar(),
                a.getNotas()
        );
    }
}
