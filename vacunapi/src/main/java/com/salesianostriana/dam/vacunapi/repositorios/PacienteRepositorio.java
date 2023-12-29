package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

    @Query("SELECT COUNT(a) FROM Administracion a WHERE a.paciente.id = ?1")
    int comprobarPacienteEnAdministracion(Long vacunaId);
}
