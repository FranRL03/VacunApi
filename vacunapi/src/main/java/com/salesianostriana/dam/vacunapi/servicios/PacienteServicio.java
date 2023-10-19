package com.salesianostriana.dam.vacunapi.servicios;


import com.salesianostriana.dam.vacunapi.dto.paciente.EditPacienteDto;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return repositorio.findAll();
    }

}
