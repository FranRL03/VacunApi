package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.dto.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.servicios.CalendarioServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendario")
@RequiredArgsConstructor
@Tag(name = "Calendario", description = "API REST de calendario con operaciones CRUD")
public class CalendarioController {

    private final CalendarioServicio calendarioServicio;

    @PostMapping("/")
    public ResponseEntity<GetCalendarioDto> addCalendario (@RequestBody GetCalendarioDto newCalendario){

        Calendario c = calendarioServicio.save(newCalendario);

        return ResponseEntity
                .status(201)
                .body(GetCalendarioDto.of(c));
    }
}
