package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Cita;
import com.salesianostriana.dam.vacunapi.modelo.Medico;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.repositorios.CitasRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.MedicoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoServicio {

    private final MedicoRepositorio medicoRepositorio;
    private final CitasRepositorio citasRepositorio;

    public Medico findById (UUID id){

        Optional<Medico> encontrado = medicoRepositorio.findById(id);

        if (!encontrado.isPresent())
            throw new PacienteNotFoundExcepcion();

        return encontrado.get();
    }
}
