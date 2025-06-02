package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CitasRepositorio extends JpaRepository<Cita, UUID> {

    @Query("""
            SELECT c
            FROM Cita c
            WHERE c.medico.id = ?1
            AND c.dia = CURRENT_DATE
            ORDER BY c.dia ASC, c.hora ASC
            """)
    List<Cita> findTodayCitasByMedicoId(UUID id);
}
