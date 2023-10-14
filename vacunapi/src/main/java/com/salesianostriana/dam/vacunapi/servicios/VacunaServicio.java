package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacunaServicio {

    private final VacunaRepositorio repositorio;

    private final CalendarioRepositorio calendarioRepositorio;

    private final CalendarioServicio calendarioServicio;

    public Vacuna save(EditVacunaDto nuevo){

        Vacuna v = new Vacuna();

        v.setNombre(nuevo.nombre());
        v.setDescripcionEnfermedad(nuevo.descripcionEnfermedad());

        List<Calendario> calendarios = nuevo.calendarios()
                .stream()
                .map(calendarioRepositorio::getReferenceById)
                .toList();


        v.setMomentos(calendarios);

        return repositorio.save(v);
    }

}
