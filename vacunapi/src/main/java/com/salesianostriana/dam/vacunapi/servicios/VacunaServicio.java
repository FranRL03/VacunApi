package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.vacuna.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.EmptyVacunaListException;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.VacunaNotDeleteException;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.VacunaNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacunaServicio {

    private final VacunaRepositorio repositorio;

    private final CalendarioRepositorio calendarioRepositorio;

    public Vacuna save(EditVacunaDto nuevo){

        Vacuna v = new Vacuna();

        v.setNombre(nuevo.nombre());
        v.setDescripcion(nuevo.descripcion());

        return repositorio.save(v);
    }

    public void updateVacuna(Vacuna vacuna) {
        repositorio.save(vacuna);
    }

    public List<Vacuna> findAll(){

        List<Vacuna> vacunas = repositorio.findAll();

        if (vacunas.isEmpty())
            throw new EmptyVacunaListException();

        return vacunas;
    }

    public Vacuna findById(UUID id){

        Optional <Vacuna> encontrado = repositorio.findById(id);

        if (!encontrado.isPresent())
            throw new VacunaNotFoundExcepcion();

        return encontrado.get();

    }

    public Optional<Vacuna> findVacunaByIdWithMomentos(UUID id) {
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

    public Vacuna edit(EditVacunaDto editVacuna, UUID id) {
        Optional<Vacuna> optionalVacuna = repositorio.findById(id);

        if (optionalVacuna.isPresent()) {
            Vacuna edit = optionalVacuna.get();
            edit.setNombre(editVacuna.nombre());
            edit.setDescripcion(editVacuna.descripcion());
            return repositorio.save(edit);
        }else{
            throw new VacunaNotFoundExcepcion();
        }
    }

    public void delete (UUID id){

        int num = repositorio.contarCalendariosConAdministraciones(id);
        if (num == 0)
            repositorio.deleteById(id);
        else
            throw new VacunaNotDeleteException();
    }


}
