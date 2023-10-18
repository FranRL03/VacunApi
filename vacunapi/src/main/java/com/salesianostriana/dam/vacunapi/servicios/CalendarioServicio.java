package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.GetCalendarioDto;
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
    private final VacunaRepositorio vacunaRepositorio;

    @Transactional // La ha puesto Luismi XD
    public Calendario save (GetCalendarioDto nuevo){

        Calendario c = new Calendario();

        c.setEdad(nuevo.edad());
        c.setTipoDosis(nuevo.tipoDosis());
        c.setRecomendaciones(nuevo.recomendaciones());
        c.setDiscriminante(nuevo.discriminante());

        //Vacuna vacuna = vacunaServicio.getReferenceByIdCreate(nuevo.idVacuna());
//        if (vacuna != null)
//            c.setVacuna(vacuna);


        Optional<Vacuna> vacuna = vacunaServicio.findById(nuevo.idVacuna());
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

}
