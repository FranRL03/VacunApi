package com.salesianostriana.dam.vacunapi.domain.appointment.repositorios;

import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {


    @Query("SELECT a FROM Appointment a WHERE a.startDateTime >= ?1 AND a.startDateTime < ?2")
    List<Appointment> findByDateRange(LocalDateTime start, LocalDateTime end);
}
