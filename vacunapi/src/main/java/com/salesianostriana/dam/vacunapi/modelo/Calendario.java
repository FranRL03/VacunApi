package com.salesianostriana.dam.vacunapi.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@NoArgsConstructor
public class Calendario {

    @Id
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
    @Column(columnDefinition = "uuid")
    private UUID id;

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
