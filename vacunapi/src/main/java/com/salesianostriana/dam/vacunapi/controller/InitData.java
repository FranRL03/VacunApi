package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.AdministracionRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.CalendarioRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final VacunaRepositorio vacunaRepositorio;
    private final CalendarioRepositorio calendarioRepositorio;

    private final PacienteRepositorio pacienteRepositorio;
    private final AdministracionRepositorio administracionRepositorio;

    @PostConstruct
    public void init(){

        Vacuna v = Vacuna.builder()
                .nombre("Alergia")
                .descripcionEnfermedad("Alergia contra el polen y los ácaros")
                //.momentos(List.of(c1, c2))
                .build();

        vacunaRepositorio.save(v);

        Calendario c1 = Calendario.builder()
                .edad(2)
                .tipoDosis("Primera")
                .recomendaciones("Reposo durante el día")
                .discriminante("T")
                .vacuna(v)
                .build();
        Calendario c2 = Calendario.builder()
                .edad(6)
                .tipoDosis("Segunda")
                .recomendaciones("Ponerse frío")
                .discriminante("H")
                .vacuna(v)
                .build();

        calendarioRepositorio.saveAll(List.of(c1, c2));

        v.setMomentos(List.of(c1, c2));

        vacunaRepositorio.save(v);


        Paciente p1 = Paciente.builder()
                .nombre("Fran")
                .apellidos("Ruiz")
                .telefonoContacto("987654321")
                .fechaNacimiento(LocalDate.of(2003, 2, 7))
                .notas("Este paciente está en tratamiento")
                .build();

        Paciente p2 = Paciente.builder()
                .nombre("Paciente")
                .apellidos("1")
                .telefonoContacto("1232456789")
                .fechaNacimiento(LocalDate.of(2023, 7, 15))
                .notas("Este paciente está en revision")
                .build();

        pacienteRepositorio.saveAll(List.of(p1, p2));

        Administracion a = Administracion.builder()
                .fecha(LocalDate.of(2023, 10, 19))
                .edadAlAdministrar(10)
                .notas("EDFWREREV")
                .momento(c1)
                .paciente(p1)
                .build();

        Administracion a2 = Administracion.builder()
                .fecha(LocalDate.of(2023, 11, 19))
                .edadAlAdministrar(10)
                .notas("efweferf")
                .momento(c2)
                .paciente(p2)
                .build();

        administracionRepositorio.saveAll(List.of(a, a2));

        p1.setVacunasAdministradas(List.of(a));
        p2.setVacunasAdministradas(List.of(a, a2));

        pacienteRepositorio.saveAll(List.of(p1, p2));

    }
}
