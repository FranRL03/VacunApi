package com.salesianostriana.dam.vacunapi.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id @GeneratedValue
    private Long id;

    private String nombre, apellidos, telefonoContacto;

    private LocalDate fechaNacimiento;

    private String notas;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER)
    private List<Administracion> vacunasAdministradas = new ArrayList<>();

}