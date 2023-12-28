package com.salesianostriana.dam.vacunapi.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id @GeneratedValue
    @Schema(example = "1", description = "Identificador clave primaria paciente")
    private Long id;

    @Schema(example = "Fran", description = "Nombre del paciente")
    private String nombre;

    @Schema(example = "Ruiz", description = "Apellido del paciente")
    private String apellidos;

    @Schema(example = "987654321", description = "Número de teléfono para contactar con el paciente")
    private String telefonoContacto;

    @Schema(example = "07-02-2003", description = "Fecha de nacimiento del paciente dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    @Schema(example = "El paciente está en tratamiento", description = "Anotación sobre el paciente")
    private String notas;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Administracion> vacunasAdministradas = new ArrayList<>();

}