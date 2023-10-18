package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.dto.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<EditCalendarioDto> addCalendario (@RequestBody GetCalendarioDto newCalendario){

        Calendario c = calendarioServicio.save(newCalendario);

        return ResponseEntity
                .status(201)
                .body(EditCalendarioDto.of(c));
    }

    @Operation(summary = "Muestra una lista de todos los calendarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de calendarios",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                      "id": 1,
                                                      "edad": 2,
                                                      "tipoDosis": "Segunda",
                                                      "recomendaciones": "Reposo durante el día",
                                                      "discriminante": "T",
                                                      "nombre": "Alergia"
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
    @JsonView(CalendarioView.CalendarioWithNameVacuna.class)
    public ResponseEntity<List<GetCalendarioDto>> findAll(){

        if (calendarioServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(
                calendarioServicio.findAll()
                        .stream()
                        .map(GetCalendarioDto::of)
                        .toList()
        );
    }

    @Operation(summary = "De un Calendario obtienes el número de vacunas que se han puesto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Calendario con número vacunas",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                     "id": 1,
                                                     "edad": 2,
                                                     "tipoDosis": "Primera",
                                                     "recomendaciones": "Reposo durante el día",
                                                     "discriminante": "T",
                                                     "dosisTotales": 2
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error al obtener un calendario",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VacunaCalendarioDto> obtenerVacunaion(@PathVariable Long id){

        Calendario momento = calendarioServicio.getVacunaCalendarioById(id);

        if(momento != null){

            VacunaCalendarioDto dto = new VacunaCalendarioDto(
                    momento.getId(),
                    momento.getEdad(),
                    momento.getTipoDosis(),
                    momento.getRecomendaciones(),
                    momento.getDiscriminante(),
                    momento.getVacuna().getMomentos().size()

            );

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener todos los momentos de vacunación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Full momentos vacunación",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "vacuna": {
                                                         "id": 1,
                                                         "nombre": "Alergia",
                                                         "descripcionEnfermedad": "Alergia contra el polen y los ácaros",
                                                         "calendario": [
                                                             {
                                                                "id": 1,
                                                                "tipoDosis": "Primera",
                                                                "recomendaciones": "Reposo durante el día",
                                                                "discriminante": "T"
                                                             },
                                                             {
                                                                "id": 2,
                                                                "tipoDosis": "Segunda",
                                                                "recomendaciones": "Ponerse frío",
                                                                "discriminante": "H"
                                                             }
                                                         ]
                                                    }
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error al obtener el momento de vacunación",
                    content = @Content)
    })
    @GetMapping("/vacuna/{id}")
    public ResponseEntity<VacunaDetailsDto> obtenerFullVacunaion(@PathVariable Long id){

        Calendario momento = calendarioServicio.getVacunaCalendarioById(id);

        if(momento != null){

            VacunaDetailsDto dto = new VacunaDetailsDto(

                    GetVacunaDto.of(momento.getVacuna())

            );

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    
}
