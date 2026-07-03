package com.salesianostriana.dam.vacunapi.domain.audit.service;

import com.salesianostriana.dam.vacunapi.domain.audit.model.Audit;
import com.salesianostriana.dam.vacunapi.domain.audit.model.AuditAction;
import com.salesianostriana.dam.vacunapi.domain.audit.model.AuditEntity;
import com.salesianostriana.dam.vacunapi.domain.audit.repository.AuditRepository;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public void audit (User user, AuditAction action, AuditEntity entity, UUID entityId, String details){

        Audit audit = Audit.builder()
                .user(user)
                .action(action)
                .entity(entity)
                .entityId(entityId)
                .details(details)
                .createdAt(LocalDateTime.now())
                .build();

        auditRepository.save(audit);
    }
}
