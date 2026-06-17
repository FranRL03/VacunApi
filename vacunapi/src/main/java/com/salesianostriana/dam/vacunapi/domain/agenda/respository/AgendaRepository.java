package com.salesianostriana.dam.vacunapi.domain.agenda.respository;

import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendaRepository extends JpaRepository<DoctorAgenda, UUID> {
}
