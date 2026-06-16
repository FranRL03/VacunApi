package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CitasRepositorio extends JpaRepository<Appointment, UUID> {


}
