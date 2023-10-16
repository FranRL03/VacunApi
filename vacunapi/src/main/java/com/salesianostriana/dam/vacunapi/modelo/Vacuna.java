package com.salesianostriana.dam.vacunapi.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "vacuna", fetch = FetchType.EAGER)
    private List<Calendario> momentos = new ArrayList<>();


}
