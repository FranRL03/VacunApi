package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.dto.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.dto.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import com.salesianostriana.dam.vacunapi.servicios.VacunaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacuna")
@RequiredArgsConstructor
public class VacunaController {

    private final VacunaRepositorio vacunaRepositorio;

    private final VacunaServicio vacunaServicio;

    @PostMapping("/")
    public ResponseEntity<GetVacunaDto> addVacuna (@RequestBody EditVacunaDto newVacuna){

        Vacuna v = vacunaServicio.save(newVacuna);

        return ResponseEntity
                .status(201)
                .body(GetVacunaDto.of(v));
    }
}
