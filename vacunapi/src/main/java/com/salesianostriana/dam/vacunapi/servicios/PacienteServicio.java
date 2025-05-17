package com.salesianostriana.dam.vacunapi.servicios;


import com.salesianostriana.dam.vacunapi.dto.paciente.EditPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.usuario.EditLoggedUserDto;
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
import java.util.UUID;

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

    public Paciente findById (UUID id){

        Optional <Paciente> encontrado = repositorio.findById(id);

        if (!encontrado.isPresent())
            throw new PacienteNotFoundExcepcion();

        return encontrado.get();
    }

    public Paciente editLoggedUser (EditLoggedUserDto edit, Paciente p){

        Paciente editado = Paciente.builder()
                .id(p.getId())
                .username(edit.username())
                .nombre(edit.nombre())
                .email(p.getEmail())
                .apellidos(edit.apellidos())
                .dni(p.getDni())
                .direccion(edit.direccion())
                .telefonoContacto(edit.telefono())
                .fechaNacimiento(p.getFechaNacimiento())
                .vacunasAdministradas(p.getVacunasAdministradas())
                .build();
        return repositorio.save(editado);
    }

    public void delete (UUID id){
        int num = repositorio.comprobarPacienteEnAdministracion(id);
        if (num == 0)
            repositorio.deleteById(id);
        else
            throw new PacienteNotDeleteException();
    }

}
