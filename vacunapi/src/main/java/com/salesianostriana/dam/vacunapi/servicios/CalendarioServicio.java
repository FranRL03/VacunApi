package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarioServicio {

    private final CalendarioRepositorio repositorio;
    private final VacunaServicio vacunaServicio;

    public Calendario save (GetCalendarioDto nuevo){

        Calendario c = new Calendario();

        c.setEdad(nuevo.edad());
        c.setTipoDosis(nuevo.tipoDosis());
        c.setRecomendaciones(nuevo.recomendaciones());
        c.setDiscriminante(nuevo.discriminante());
        vacunaServicio.findById(nuevo.idVacuna()).orElse(null);

        return repositorio.save(c);

    }

}
