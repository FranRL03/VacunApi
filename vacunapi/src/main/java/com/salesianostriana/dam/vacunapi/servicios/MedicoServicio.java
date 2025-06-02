package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.modelo.Cita;
import com.salesianostriana.dam.vacunapi.repositorios.CitasRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.MedicoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoServicio {

    private final MedicoRepositorio medicoRepositorio;
    private final CitasRepositorio citasRepositorio;

    public List<Cita> findToday (UUID id) {

        return citasRepositorio.findTodayCitasByMedicoId(id);
    }
}
