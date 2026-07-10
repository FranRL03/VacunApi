package com.salesianostriana.dam.vacunapi.domain.list.repository;

import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import com.salesianostriana.dam.vacunapi.domain.list.model.StatusList;
import com.salesianostriana.dam.vacunapi.domain.list.model.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface WaitingListRepository extends JpaRepository<WaitingList, UUID> {

    boolean existsByPatientIdAndPreferredDateAndStatus(UUID patientId, LocalDate preferredDate, StatusList status);
}
