package com.salesianostriana.dam.vacunapi.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
public class Patient extends User {

    @Schema(example = "Fran", description = "Nombre del paciente")
    private String nombre;

    @Schema(example = "Ruiz", description = "Apellido del paciente")
    private String apellidos;

    @Schema(example = "987654321", description = "Número de teléfono para contactar con el paciente")
    private String telefonoContacto;

    @Schema(example = "07-02-2003", description = "Fecha de nacimiento del paciente dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    @Column(name = "dni", unique = true, updatable = false)
    private String dni;

    private String direccion;

    @Schema(example = "El paciente está en tratamiento", description = "Anotación sobre el paciente")
    private String notas;

}