package com.salesianostriana.dam.vacunapi.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administracion {

    @Id @GeneratedValue
    private Long id;

    private LocalDate fecha;

    private int edadAlAdministrar; // en meses

    private String notas;

    @ManyToOne
    private Calendario momento;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

}
