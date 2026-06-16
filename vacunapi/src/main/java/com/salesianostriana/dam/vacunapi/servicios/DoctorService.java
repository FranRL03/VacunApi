package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Doctor;
import com.salesianostriana.dam.vacunapi.repositorios.CitasRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.MedicoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final MedicoRepositorio medicoRepositorio;
    private final CitasRepositorio citasRepositorio;

    public Doctor findById (UUID id){

        Optional<Doctor> encontrado = medicoRepositorio.findById(id);

        if (!encontrado.isPresent())
            throw new PacienteNotFoundExcepcion();

        return encontrado.get();
    }
}
