package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.paciente.EditPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetUpdatePacienteDto;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
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
import org.springframework.format.annotation.DateTimeFormat;
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
    private final PacienteRepositorio pacienteRepositorio;

    @Operation(summary = "Añades un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Creación de paciente",
                    content = {@Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Paciente.class)),
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
                            array = @ArraySchema(schema = @Schema(implementation = Paciente.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                      "id": 1,
                                                      "nombre": "Fran",
                                                      "apellidos": "Ruiz",
                                                      "edad": "20 años",
                                                      "cantidadVacuna": 1
                                                  },
                                                  {
                                                      "id": 2,
                                                      "nombre": "Paciente",
                                                      "apellidos": "1",
                                                      "edad": "5 meses",
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
    public List<GetPacienteFindAll> findAll() {

        return pacienteServicio.findAll()
                        .stream()
                        .map(GetPacienteFindAll::of)
                        .toList();

    }

    @Operation(summary = "Buscas un paciente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Paciente por id",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Paciente.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                  "id": 1,
                                                  "nombre": "Fran",
                                                  "apellidos": "Ruiz",
                                                  "telefonoContacto": "987654321",
                                                  "fechaNacimiento": "07-02-2003",
                                                  "notas": "Este paciente está en tratamiento",
                                                  "vacunasAdministradas": [
                                                      {
                                                          "id": 1,
                                                          "fecha": "19-10-2023",
                                                          "edadAlAdministrar": "10 meses",
                                                          "vacuna": "Alergia",
                                                          "tipoDosis": "Primera"
                                                      }
                                                  ]
                                              }
                                              
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error al buscar un paciente",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @JsonView(PacienteView.findByIdWithAllEntities.class)
    public GetPacienteDto findById(@PathVariable Long id){

        Paciente p = pacienteServicio.findById(id);

        return GetPacienteDto.find(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUpdatePacienteDto> edit (@PathVariable Long id,
                                                      @RequestBody EditPacienteDto editPaciente){

        if (pacienteServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(
                GetUpdatePacienteDto.of(
                        pacienteServicio.edit(id, editPaciente)));
    }

    @Operation(summary = "Borra un paciente por su id")
    @ApiResponse(responseCode = "204 No Content",
            description = "Borrado con éxito",
            content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        pacienteServicio.delete(id);

        return ResponseEntity.noContent().build();
    }


}
