package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.vacuna.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.exception.EmptyVacunaListException;
import com.salesianostriana.dam.vacunapi.exception.VacunaNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacunaServicio {

    private final VacunaRepositorio repositorio;

    private final CalendarioRepositorio calendarioRepositorio;

    public Vacuna save(EditVacunaDto nuevo){

        Vacuna v = new Vacuna();

        v.setNombre(nuevo.nombre());
        v.setDescripcionEnfermedad(nuevo.descripcionEnfermedad());

        return repositorio.save(v);
    }

    public List<Vacuna> findAll(){

        List<Vacuna> vacunas = repositorio.findAll();

        if (vacunas.isEmpty())
            throw new EmptyVacunaListException();

        return vacunas;
    }

    public Vacuna findById(Long id){

        Optional <Vacuna> encontrado = repositorio.findById(id);

        if (!encontrado.isPresent())
            throw new VacunaNotFoundExcepcion(id);

        return encontrado.get();

    }

    public Optional<Vacuna> findVacunaByIdWithMomentos(Long id) {
        Optional<Vacuna> optionalVacuna = repositorio.findById(id);

        if (optionalVacuna.isPresent()) {
            Vacuna vacuna = optionalVacuna.get();
            // La lista de momentos asociados se puede cargar aquí si es necesario.
            vacuna.getMomentos().size(); // Esto forzará la carga de la lista de momentos.

            return Optional.of(vacuna);
        } else {
            return Optional.empty(); // Devuelve un Optional vacío si la vacuna no se encuentra.
        }
    }

//    public Vacuna edit(EditVacunaDto editVacuna, Long id){
//        if(repositorio.findById(id).isPresent()) {
//            Optional<Vacuna> encontrado = Optional.of(repositorio.getReferenceById(id));
//            Vacuna edit = encontrado.get();
//            edit.setNombre(editVacuna.nombre());
//            edit.setDescripcionEnfermedad(editVacuna.descripcionEnfermedad());
//            return repositorio.save(edit);
//        }
//    return null;
//    }

    public Vacuna edit(EditVacunaDto editVacuna, Long id) {
        Optional<Vacuna> optionalVacuna = repositorio.findById(id);

        if (optionalVacuna.isPresent()) {
            Vacuna edit = optionalVacuna.get();
            edit.setNombre(editVacuna.nombre());
            edit.setDescripcionEnfermedad(editVacuna.descripcionEnfermedad());
            return repositorio.save(edit);
        }else{
            throw new VacunaNotFoundExcepcion(id);
        }
    }


    public Vacuna getReferenceByIdCreate(Long id) {

        return repositorio.getReferenceById(id);
    }


}
