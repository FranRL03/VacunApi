package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.dto.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.dto.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.VacunaRepositorio;
import com.salesianostriana.dam.vacunapi.servicios.VacunaServicio;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
http://localhost:8080/swagger-ui/index.html
 */
@RestController
@RequestMapping("/vacuna")
@RequiredArgsConstructor
@Tag(name = "Vacuna", description = "API REST de vacuna con operaciones CRUD")
public class VacunaController {

    private final VacunaRepositorio vacunaRepositorio;

    private final VacunaServicio vacunaServicio;

    @Operation(summary = "Añades una vacuna")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Creación de vacuna",
                    content = { @Content(mediaType = "aplication/json",
                        array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1, 
                                                    "nombre": "Vacunesil",
                                                    "descripcion": "Vacuna 
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "400",
                    description = "Error al crear una vacuna",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<GetVacunaDto> addVacuna (@RequestBody EditVacunaDto newVacuna){

        Vacuna v = vacunaServicio.save(newVacuna);

        return ResponseEntity
                .status(201)
                .body(GetVacunaDto.of(v));
    }
}
