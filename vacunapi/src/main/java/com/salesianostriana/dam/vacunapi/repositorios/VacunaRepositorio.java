package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacunaRepositorio extends JpaRepository<Vacuna, Long> {
}
