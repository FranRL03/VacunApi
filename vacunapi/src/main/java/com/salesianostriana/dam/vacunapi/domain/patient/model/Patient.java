package com.salesianostriana.dam.vacunapi.domain.patient.model;

import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
public class Patient {

    @Id
    @Column(name = "id")
    private UUID id;

    @Schema(example = "Fran", description = "Nombre del paciente")
    private String name;

    @Schema(example = "Ruiz", description = "Apellido del paciente")
    private String lastName;

    @Schema(example = "987654321", description = "Número de teléfono para contactar con el paciente")
    private String phone;

    @Schema(example = "07-02-2003", description = "Fecha de nacimiento del paciente dd-MM-yyyy")
    private LocalDate birthday;

    @Column(name = "dni", unique = true, updatable = false)
    private String dni;

    private String address;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}