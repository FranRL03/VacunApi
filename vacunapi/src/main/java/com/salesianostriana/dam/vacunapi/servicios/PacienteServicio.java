package com.salesianostriana.dam.vacunapi.servicios;


import com.salesianostriana.dam.vacunapi.dto.paciente.EditPacienteDto;
import com.salesianostriana.dam.vacunapi.exception.PacienteException.EmptyPacienteListException;
import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotDeleteException;
import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.VacunaNotDeleteException;
import com.salesianostriana.dam.vacunapi.exception.VacunaException.VacunaNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServicio {

    private final PacienteRepositorio repositorio;

    public Paciente save (EditPacienteDto nuevo){

        Paciente p = new Paciente();

        p.setNombre(nuevo.nombre());
        p.setApellidos(nuevo.apellidos());
        p.setTelefonoContacto(nuevo.telefonoContacto());
        p.setFechaNacimiento(nuevo.fechaNacimiento());
        p.setNotas(nuevo.notas());

        return repositorio.save(p);
    }

    public List<Paciente> findAll (){

        List<Paciente> pacientes = repositorio.findAll();

        if (pacientes.isEmpty())
            throw new EmptyPacienteListException();

        return pacientes;
    }

    public Paciente findById (Long id){

        Optional <Paciente> encontrado = repositorio.findById(id);

        if (!encontrado.isPresent())
            throw new PacienteNotFoundExcepcion();

        return encontrado.get();
    }

    public Paciente edit (Long id, EditPacienteDto editPaciente){

        Optional<Paciente> p = Optional.ofNullable(findById(id));

        if(p.isPresent()){
            Paciente edit = p.get();
            edit.setNombre(editPaciente.nombre());
            edit.setApellidos(editPaciente.apellidos());
            edit.setTelefonoContacto(editPaciente.telefonoContacto());
            edit.setNotas(editPaciente.notas());
            return repositorio.save(edit);
        }else{
            throw new PacienteNotFoundExcepcion();
        }
    }

    public void delete (Long id){
        int num = repositorio.comprobarPacienteEnAdministracion(id);
        if (num == 0)
            repositorio.deleteById(id);
        else
            throw new PacienteNotDeleteException();
    }

}
