package com.salesianostriana.dam.vacunapi.domain.audit.model;

import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    UUID id;

    @Enumerated(EnumType.STRING)
    AuditAction action;

    @Enumerated(EnumType.STRING)
    AuditEntity entity;

    UUID entityId;

    String details;

    //@CreatedDate (cuando la auditoria sea autimatica)
    @Column(updatable = false)
    LocalDateTime createdAt;

    //@CreatedBy (cuando la auditoria sea autimatica)
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
