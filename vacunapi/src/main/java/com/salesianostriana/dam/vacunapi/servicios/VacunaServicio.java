package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.dto.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
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

//        List<Calendario> calendarios = nuevo.calendarios()
//                .stream()
//                .map(calendarioRepositorio::getReferenceById)
//                .toList();
//
//
//        v.setMomentos(calendarios);

        return repositorio.save(v);
    }

    public List<Vacuna> findAll(){

        List<Vacuna> vacunas = repositorio.findAll();

        return vacunas;

    }

    public Optional<Vacuna> findById(Long id){

      Optional<Vacuna> encontrado = repositorio.findById(id);

      return encontrado;

    }

    public Vacuna edit(EditVacunaDto editVacuna, Long id){
        if(repositorio.findById(id).isPresent()) {
            Optional<Vacuna> encontrado = repositorio.findById(id);
            Vacuna edit = encontrado.get();
            edit.setNombre(editVacuna.nombre());
            edit.setDescripcionEnfermedad(editVacuna.descripcionEnfermedad());
            return repositorio.save(edit);
        }
    return null;
    }

}
