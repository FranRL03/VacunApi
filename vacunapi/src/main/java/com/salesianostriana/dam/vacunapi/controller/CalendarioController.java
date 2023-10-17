package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.dto.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.servicios.CalendarioServicio;
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

@RestController
@RequestMapping("/calendario")
@RequiredArgsConstructor
@Tag(name = "Calendario", description = "API REST de calendario con operaciones CRUD")
public class CalendarioController {

    private final CalendarioServicio calendarioServicio;

    @Operation(summary = "Añades un calendario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Creación de calendario",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "edad": 2,
                                                    "tipoDosis": "Segunda",
                                                    "recomendaciones": "efef",
                                                    "discriminante": "T",
                                                    "idVacuna": 1 
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "400",
                    description = "Error al crear un calendario",
                    content = @Content)
    })
    @PostMapping("/")
    @JsonView(CalendarioView.VacunaCalendario.class)
    public ResponseEntity<GetCalendarioDto> addCalendario (@RequestBody GetCalendarioDto newCalendario){

        Calendario c = calendarioServicio.save(newCalendario);

        return ResponseEntity
                .status(201)
                .body(GetCalendarioDto.of(c));
    }

    
}
