package com.salesianostriana.dam.vacunapi.modelo;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacuna {

    @Id @GeneratedValue
    @Schema(example = "1", description = "Identificador clave primaria vacuna")
    private Long id;

    @Schema(example = "Varicela", description = "Nombre de la vacuna")
    private String nombre;

    @Schema(example = "Puntitos rojos con irritación")
    private String descripcionEnfermedad;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "vacuna", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calendario> momentos = new ArrayList<>();


}
