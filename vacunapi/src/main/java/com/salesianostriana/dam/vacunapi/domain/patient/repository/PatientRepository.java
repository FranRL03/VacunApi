package com.salesianostriana.dam.vacunapi.domain.patient.repository;

import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {



}
