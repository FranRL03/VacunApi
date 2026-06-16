package com.salesianostriana.dam.vacunapi.domain.doctor.model;

import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
public class Doctor {

    @Id
    @Column(name = "id")
    private UUID id;

    @Schema(example = "Fran", description = "Nombre del medico")
    private String name;

    @Schema(example = "Ruiz", description = "Apellido del medio")
    private String lastName;

    private String speciality;
    @Schema(example = "987654321", description = "Número de teléfono para contactar con el medico")
    private String phone;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
