package com.salesianostriana.dam.vacunapi.domain.audit.repository;

import com.salesianostriana.dam.vacunapi.domain.audit.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, UUID> {
}
