package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.dto.GetCalendarioDeVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Function;

public interface CalendarioRepositorio extends JpaRepository<Calendario, Long> {
}
