package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.paciente.EditPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.servicios.PacienteServicio;
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

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@Tag(name = "Paciente", description = "API REST de paciente con operaciones CRUD")
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @Operation(summary = "Añades un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Creación de paciente",
                    content = {@Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                  "id": 1,
                                                  "nombre": "Fran",
                                                  "apellidos": "Ruiz",
                                                  "telefonoContacto": "987654321",
                                                  "fechaNacimiento": "2003-02-07",
                                                  "notas": "Este paciente esta en tratamiento"
                                                }
                                            ]
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "400",
                    description = "Error al crear un paciente",
                    content = @Content)
    })
    @PostMapping("/")
    @JsonView({PacienteView.informacionPaciente.class})
    public ResponseEntity<GetPacienteDto> addPaciente(@RequestBody EditPacienteDto newPaciente) {

        Paciente p = pacienteServicio.save(newPaciente);

        return ResponseEntity
                .status(201)
                .body(GetPacienteDto.of(p));
    }

    @Operation(summary = "Muestra una lista de los pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de los pacientes que hay en la base de datos",
                    content = {@Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                            
                                                    "id": 1,
                                                    "nombre": "Fran",
                                                    "apellidos": "Ruiz",
                                                    "edad": 20,
                                                    "cantidadVacuna": 1
                                                
                                                },
                                                {
                                                     "id": 2,
                                                     "nombre": "Paciente",
                                                     "apellidos": "1",
                                                     "edad": 3,
                                                     "cantidadVacuna": 1
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
    public ResponseEntity<List<GetPacienteFindAll>> findAll() {

        if (pacienteServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(
                pacienteServicio.findAll()
                        .stream()
                        .map(GetPacienteFindAll::of)
                        .toList()
        );

    }

    @Operation(summary = "Buscas un paciente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Paciente por id",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vacuna.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {  
                                                  "id": 1,
                                                  "nombre": "Fran",
                                                  "apellidos": "Ruiz",
                                                  "telefonoContacto": "987654321",
                                                  "fechaNacimiento": "2003-02-07",
                                                  "notas": "Este paciente está en tratamiento",
                                                  "administracion": [
                                                      {
                                                          "id": 1,
                                                          "fecha": "2023-10-19",
                                                          "edadAlAdministrar": 10,
                                                          "getCalendario": {
                                                              "tipoDosis": "Primera",
                                                              "nombre": "Alergia"
                                                          }
                                                      }
                                                  ]
                                              }
                                              
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error al buscar una vacuna",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @JsonView(PacienteView.findByIdWithAllEntities.class)
    public ResponseEntity<GetPacienteDto> findById(@PathVariable Long id){

        if (pacienteServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(pacienteServicio.findById(id)
                .map(GetPacienteDto::find));
    }

}
