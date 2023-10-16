package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CalendarioRepositorio extends JpaRepository<Calendario, Long> {

}
