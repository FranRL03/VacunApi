package com.salesianostriana.dam.vacunapi.domain.doctor.servicios;

import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.appointment.repositorios.AppointmentRepository;
import com.salesianostriana.dam.vacunapi.domain.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository medicoRepositorio;
    private final AppointmentRepository citasRepositorio;

    public Doctor findById (UUID id){

        Optional<Doctor> encontrado = medicoRepositorio.findById(id);

        if (!encontrado.isPresent())
            throw new PacienteNotFoundExcepcion();

        return encontrado.get();
    }
}
