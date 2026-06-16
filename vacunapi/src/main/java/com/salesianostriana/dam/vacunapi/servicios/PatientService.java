package com.salesianostriana.dam.vacunapi.servicios;


import com.salesianostriana.dam.vacunapi.dto.usuario.EditLoggedUserDto;
import com.salesianostriana.dam.vacunapi.exception.PacienteException.EmptyPacienteListException;
import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Patient;
import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

//    private final PacienteRepositorio repositorio;
//
//    public Patient save (EditPacienteDto nuevo){
//
//        Patient p = new Patient();
//
//        p.setNombre(nuevo.nombre());
//        p.setApellidos(nuevo.apellidos());
//        p.setTelefonoContacto(nuevo.telefonoContacto());
//        p.setFechaNacimiento(nuevo.fechaNacimiento());
//        p.setNotas(nuevo.notas());
//
//        return repositorio.save(p);
//    }
//
//    public List<Patient> findAll (){
//
//        List<Patient> pacientes = repositorio.findAll();
//
//        if (pacientes.isEmpty())
//            throw new EmptyPacienteListException();
//
//        return pacientes;
//    }
//
//    public Patient findById (UUID id){
//
//        Optional <Patient> encontrado = repositorio.findById(id);
//
//        if (!encontrado.isPresent())
//            throw new PacienteNotFoundExcepcion();
//
//        return encontrado.get();
//    }
//
//    public Patient editLoggedUser (EditLoggedUserDto edit, Patient p){
//
//        Patient editado = Patient.builder()
//                .id(p.getId())
//                .username(edit.username())
//                .nombre(edit.nombre())
//                .email(p.getEmail())
//                .apellidos(edit.apellidos())
//                .dni(p.getDni())
//                .direccion(edit.direccion())
//                .telefonoContacto(edit.telefono())
//                .fechaNacimiento(p.getFechaNacimiento())
//                .build();
//        return repositorio.save(editado);
//    }

}
