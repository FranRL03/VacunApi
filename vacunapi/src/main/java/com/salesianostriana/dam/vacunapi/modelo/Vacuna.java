package com.salesianostriana.dam.vacunapi.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacuna {

    @Id @GeneratedValue
    private Long id;

    private String nombre, descripcionEnfermedad;

    @OneToMany(mappedBy = "vacuna", fetch = FetchType.EAGER)
    private List<Calendario> momentos = new ArrayList<>();


}
