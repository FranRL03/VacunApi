package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.servicios.AdministracionServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Muestra una lista de todas las administraciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de administraciones",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "fecha": "2023-10-19",
                                                    "edadAlAdministrar": 10,
                                                    "paciente": {
                                                        "id": 1,
                                                        "nombre": "Fran",
                                                        "apellidos": "Ruiz",
                                                        "cantidadVacuna": 1
                                                    },
                                                    "calendarioDto": {
                                                        "vacuna": {
                                                            "id": 1,
                                                            "nombre": "Alergia"
                                                        }
                                                    }
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error lista vacía",
                    content = @Content)
    })
    @GetMapping("/")
    @JsonView({AdministracionView.findAll.class})
    public ResponseEntity<List<GetAdministracionDto>> findAll(){

        if(administracionServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(
                administracionServicio.findAll()
                        .stream()
                        .map(GetAdministracionDto::of)
                        .toList()
        );
    }
}
