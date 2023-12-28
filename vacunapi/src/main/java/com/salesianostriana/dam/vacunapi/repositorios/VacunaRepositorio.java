package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface VacunaRepositorio extends JpaRepository<Vacuna, Long> {

    @Query("SELECT COUNT(c) FROM Calendario c WHERE c.vacuna.id = ?1")
    int comprobarVacunaEnCalendario(Long vacunaId);

    @Query("SELECT COUNT(c) FROM Calendario c WHERE c.vacuna.id = ?1 AND EXISTS (SELECT 1 FROM Administracion a WHERE a.momento.id = c.id)")
    int contarCalendariosConAdministraciones(Long vacunaId);

}
