package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.calendario.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.exception.CalendarioException.CalendarioNotDeleteException;
import com.salesianostriana.dam.vacunapi.exception.CalendarioException.CalendarioNotFoundException;
import com.salesianostriana.dam.vacunapi.exception.CalendarioException.EmptyCalendarioListException;
import com.salesianostriana.dam.vacunapi.exception.CalendarioException.ErrorEditCalendarioException;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.VacunaNotDeleteException;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.VacunaNotFoundExcepcion;
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

    @Transactional
    public Calendario save (GetCalendarioDto nuevo){

        Calendario c = new Calendario();

        c.setEdad(Integer.parseInt(nuevo.edad()));
        c.setTipoDosis(nuevo.tipoDosis());
        c.setRecomendaciones(nuevo.recomendaciones());
        c.setDiscriminante(nuevo.discriminante());

        Optional<Vacuna> vacuna = Optional.ofNullable(vacunaServicio.findById(nuevo.id()));
        if (vacuna.isPresent()) {
            c.setVacuna(vacuna.get());
            c.getVacuna().getNombre();
            c.getVacuna().getDescripcionEnfermedad();
        }else {
            throw new VacunaNotFoundExcepcion();
        }

        return repositorio.save(c);

    }

    public List<Calendario> findAll(){

        List<Calendario> calendarios = repositorio.findAll();

        if (calendarios.isEmpty())
            throw new EmptyCalendarioListException();

        return calendarios;
    }

    public Optional<Calendario> findById (Long id) {

        return repositorio.findById(id);
    }

    public int cantidadMomentos (Long id){
        return repositorio.cantidadDeMomentos(id);
    }


    public Calendario getVacunaCalendarioById (Long id){

        Optional<Calendario> momento = repositorio.findById(id);

        if (momento.isPresent())
             return momento.get();

        throw new CalendarioNotFoundException();

    }

    public Calendario edit (EditCalendarioDto editCalendario, Long id){
        Optional<Calendario> optionalCalendario = repositorio.findById(id);

        int num = repositorio.comprobarCalendarioEnAdministracion(id);

        if (optionalCalendario.isPresent() && num == 0) {
            Calendario edit = optionalCalendario.get();

            edit.setEdad(editCalendario.edad());
            edit.setTipoDosis(editCalendario.tipoDosis());
            edit.setRecomendaciones(editCalendario.recomendaciones());
            edit.setDiscriminante(editCalendario.discriminante());

            Optional<Vacuna> vacuna = Optional.ofNullable(vacunaServicio.findById(editCalendario.id()));

            if (vacuna.isPresent()) {
                edit.setVacuna(vacuna.get());
            }else {
                throw new VacunaNotFoundExcepcion();
            }

            return repositorio.save(edit);
        }

        throw new ErrorEditCalendarioException();
    }

    public void delete (Long id){

        int num = repositorio.comprobarCalendarioEnAdministracion(id);
        System.out.println(num);
        if (num == 0) {
            repositorio.deleteById(id);
        }else
            throw new CalendarioNotDeleteException();
    }

}
