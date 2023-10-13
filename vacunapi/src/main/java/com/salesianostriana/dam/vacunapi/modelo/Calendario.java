package com.salesianostriana.dam.vacunapi.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendario {

    @Id @GeneratedValue
    private Long id;

    private int edad; // número de meses;

    private String tipoDosis;  // 1ª, 2ª, 3ª, Refuerzo, Recordatorio

    private String recomendaciones;

    private String discriminante; // T - Todos, H - Hombres, M - Mujeres

    @ManyToOne
    private Vacuna vacuna;

}
