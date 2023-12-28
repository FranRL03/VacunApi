package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface CalendarioRepositorio extends JpaRepository<Calendario, Long> {
    @Query("""
            SELECT COUNT(c)
            FROM Calendario c
            JOIN c.vacuna as cat
            WHERE cat.id = ?1
            """)
    int cantidadDeMomentos (Long id);

    @Query("SELECT COUNT(a) FROM Administracion a WHERE a.momento.id = ?1")
    int comprobarCalendarioEnAdministracion(Long id);
}
