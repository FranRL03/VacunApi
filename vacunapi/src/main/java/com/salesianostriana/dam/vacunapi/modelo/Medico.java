package com.salesianostriana.dam.vacunapi.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
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
public class Medico extends Usuario{

    @Schema(example = "Fran", description = "Nombre del medico")
    private String nombre;

    @Schema(example = "Ruiz", description = "Apellido del medio")
    private String apellidos;

    private String especialidad;
    @Schema(example = "987654321", description = "Número de teléfono para contactar con el medico")
    private String telefono;
}
