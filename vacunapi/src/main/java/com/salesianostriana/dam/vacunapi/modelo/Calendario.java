package com.salesianostriana.dam.vacunapi.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendario {

    @Id @GeneratedValue
    @Schema(example = "1", description = "Identificador clave primaria de calendario")
    private Long id;

    @Schema(example = "6", description = "La edad en meses que se recomienda ponerse la vacuna")
    private int edad; // número de meses;

    @Schema(example = "Tercera", description = "1ª, 2ª, 3ª, Refuerzo, Recordatorio")
    private String tipoDosis;  // 1ª, 2ª, 3ª, Refuerzo, Recordatorio

    @Schema(example = "Ponerse frio después del pinchazo", description = "Recomendación de la vacuna")
    private String recomendaciones;

    @Schema(example = "T", description = "T - Todos, H - Hombres, M - Mujeres")
    private String discriminante; // T - Todos, H - Hombres, M - Mujeres


    @ManyToOne
    private Vacuna vacuna;

}
