package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.VacunaView.*;
import com.salesianostriana.dam.vacunapi.dto.vacuna.EditVacunaDto;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
http://localhost:8080/swagger-ui/index.html
 */
@RestController
@RequestMapping("/vacuna")
@RequiredArgsConstructor
@Tag(name = "Vacuna", description = "API REST de vacuna con operaciones CRUD")
public class VacunaController {

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
                                                    "id": 1, .
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
    @JsonView(vacunaCreate.class)
    public ResponseEntity<GetVacunaDto> addVacuna (@RequestBody EditVacunaDto newVacuna){

        Vacuna v = vacunaServicio.save(newVacuna);

        return ResponseEntity
                .status(201)
                .body(GetVacunaDto.of(v));
    }

    @Operation(summary = "Muestra una lista de las vacunas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de las vacunas que hay en la base de datos",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1, 
                                                    "nombre": "Viruela"
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content)
    })
    @GetMapping("/")
    @JsonView(VacunaList.class)
    public List<GetVacunaDto> findAll (){

        return vacunaServicio.findAll().stream()
                .map(GetVacunaDto::of)
                .toList();

    }

    @Operation(summary = "Buscas una vacuna por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Vacuna por id",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            { “id”: 1, 
                                            “nombre”: “Nombre vacuna”, 
                                            “descripcion”: “Esta vacuna sirve para . . .”, 
                                            “momentos”: [
                                                 {“id”: 2, 
                                                 “edad”: 2 meses”, 
                                                 “tipoDosis”: “Primera”, 
                                                 “recomendaciones”: “aplicar hielo tras la administración”, 
                                                 “discriminante”: “T”},
                                                 ...
                                              ] }
                                              
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error al buscar una vacuna",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @JsonView(VacunaDetails.class)
    public GetVacunaDto findById(@PathVariable Long id){

        Vacuna v = vacunaServicio.findById(id);

        return GetVacunaDto.of(v);

    }

    @JsonView(VacunaList.class)
    @PutMapping("/{id}")
    public ResponseEntity<GetVacunaDto> edit (@PathVariable Long id,
                                              @RequestBody EditVacunaDto editVacuna){

        return ResponseEntity.ok(
                GetVacunaDto.of(
                        vacunaServicio.edit(editVacuna, id)));

    }

    @Operation(summary = "Borra una vacuna por su id")
    @ApiResponse(responseCode = "204 No Content",
            description = "Borrado con éxito",
            content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        vacunaServicio.delete(id);

        return ResponseEntity.noContent().build();
    }
}
