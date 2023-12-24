package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.calendario.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CalendarioServicio {

    private final CalendarioRepositorio repositorio;
    private final VacunaServicio vacunaServicio;

    @Transactional // La ha puesto Luismi XD
    public Calendario save (GetCalendarioDto nuevo){

        Calendario c = new Calendario();

        c.setEdad(nuevo.edad());
        c.setTipoDosis(nuevo.tipoDosis());
        c.setRecomendaciones(nuevo.recomendaciones());
        c.setDiscriminante(nuevo.discriminante());

        Optional<Vacuna> vacuna = Optional.ofNullable(vacunaServicio.findById(nuevo.id()));
        if (vacuna.isPresent())
            c.setVacuna(vacuna.get());

        c.getVacuna().getNombre();
        c.getVacuna().getDescripcionEnfermedad();

        return repositorio.save(c);

    }

    public List<Calendario> findAll(){

        return repositorio.findAll();
    }

    public Calendario getReferenceByIdCreate(Long id) {

        return repositorio.getReferenceById(id);
    }

    public Calendario getVacunaCalendarioById (Long id){

        Optional<Calendario> momento = repositorio.findById(id);

        if (momento.isPresent())
             return momento.get();

        return null;

    }

    public Calendario edit (EditCalendarioDto editCalendario, Long id){
        Optional<Calendario> optionalCalendario = repositorio.findById(id);

        if (optionalCalendario.isPresent()) {
            Calendario edit = optionalCalendario.get();
            edit.setEdad(editCalendario.edad());
            edit.setTipoDosis(editCalendario.tipoDosis());
            edit.setRecomendaciones(editCalendario.recomendaciones());
            edit.setDiscriminante(editCalendario.discriminante());
            return repositorio.save(edit);
        }

        return null;
    }

}
