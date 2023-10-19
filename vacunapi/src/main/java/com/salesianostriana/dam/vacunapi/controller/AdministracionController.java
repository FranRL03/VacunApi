package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.administracion.EditAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.servicios.AdministracionServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administracion")
@RequiredArgsConstructor
@Tag(name = "Administracion", description = "API REST de administracion con operaciones CRUD")
public class AdministracionController {

    private final AdministracionServicio administracionServicio;

//    @PostMapping("/")
//    @JsonView({AdministracionView.administracionCreate.class})
//    public ResponseEntity<EditAdministracionDto> addAdministracion(@RequestBody GetAdministracionDto newAdministracion){
//        Administracion a = administracionServicio.save(newAdministracion);
//
//        return ResponseEntity
//                .status(201)
//                .body(EditAdministracionDto.of(a));
//    }
}
