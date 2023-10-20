package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.administracion.EditAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.AdministracionRepositorio;
import com.salesianostriana.dam.vacunapi.servicios.AdministracionServicio;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administracion")
@RequiredArgsConstructor
@Tag(name = "Administracion", description = "API REST de administracion con operaciones CRUD")
public class AdministracionController {

    private final AdministracionServicio administracionServicio;
    private final PacienteServicio pacienteServicio;

    @Operation(summary = "Muestra una lista de todas las administraciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista de administraciones",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Administracion.class)),
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

    @Operation(summary = "Buscas una administracion por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Administracion por id",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Administracion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {  
                                                   "id": 2,
                                                      "fecha": "2023-11-19",
                                                      "edadAlAdministrar": 10,
                                                      "paciente": {
                                                          "id": 2,
                                                          "nombre": "Paciente",
                                                          "apellidos": "1",
                                                          "cantidadVacuna": 1
                                                      },
                                                      "calendario": {
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
                                             }
                                              
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "Error al buscar una administracion",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @JsonView(AdministracionView.findById.class)
    public ResponseEntity<GetAdministracionDto> findById (@PathVariable Long id){

        if(administracionServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(administracionServicio.findById(id)
                .map(GetAdministracionDto::find));

    }

    @Operation(summary = "Buscas un paciente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Paciente por id",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Administracion.class)),
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
                    description = "Error al buscar un paciente",
                    content = @Content)
    })
    @GetMapping("/paciente/{id}")
    @JsonView(PacienteView.idPacienteAdministracion.class)
    public ResponseEntity<GetPacienteDto> findByIdPanciente(@PathVariable Long id){

        if (pacienteServicio.findAll().isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(pacienteServicio.findById(id)
                .map(GetPacienteDto::find));
    }

    @Operation(summary = "Borra una administracion por su id")
    @ApiResponse(responseCode = "204 No Content",
            description = "Borrado con éxito",
            content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "No se puede borrar la información de administración de una vacuna a un paciente");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }
}
