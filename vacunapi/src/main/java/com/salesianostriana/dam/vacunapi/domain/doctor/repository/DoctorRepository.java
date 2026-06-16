package com.salesianostriana.dam.vacunapi.domain.doctor.repository;

import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
