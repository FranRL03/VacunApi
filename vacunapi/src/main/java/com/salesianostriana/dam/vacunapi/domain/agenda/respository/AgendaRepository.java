package com.salesianostriana.dam.vacunapi.domain.agenda.respository;

import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendaRepository extends JpaRepository<DoctorAgenda, UUID> {

    @Query("""
            SELECT a
            FROM DoctorAgenda a
            WHERE a.doctor.id = ?1
            """)
    List<DoctorAgenda> getAgendasToDoctor (UUID doctorId);

    Optional<DoctorAgenda> findByDoctorIdAndDayOfWeekAndActive (UUID doctorId, int dayOfWeek, boolean active);
}
