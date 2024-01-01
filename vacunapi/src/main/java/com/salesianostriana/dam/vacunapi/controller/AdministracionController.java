package com.salesianostriana.dam.vacunapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.View.CalendarioView;
import com.salesianostriana.dam.vacunapi.View.PacienteView;
import com.salesianostriana.dam.vacunapi.dto.administracion.EditAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAddAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteDto;
import com.salesianostriana.dam.vacunapi.dto.vacuna.GetVacunaDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
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
import java.util.Optional;

@RestController
@RequestMapping("/administracion")
@RequiredArgsConstructor
@Tag(name = "Administracion", description = "API REST de administracion con operaciones CRUD")
public class AdministracionController {

    private final AdministracionServicio administracionServicio;
    private final PacienteServicio pacienteServicio;


    @Operation(summary = "Añades una administracion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Creación de administracion",
                    content = { @Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Administracion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 5,
                                                 "fecha": "19-11-2023",
                                                 "edadAlAdministrar": "10 meses",
                                                 "notas": "creado",
                                                 "paciente": {
                                                     "nombre": "Fran",
                                                     "apellidos": "Ruiz",
                                                     "cantidadVacuna": 3
                                                 },
                                                 "vacuna": {
                                                     "id": 1,
                                                     "nombre": "Alergia"
                                                 }
                                             }
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "400",
                    description = "Error al crear un administracion",
                    content = @Content)
    })
    @PostMapping("/")
    @JsonView( AdministracionView.create.class)
    public ResponseEntity<GetAddAdministracionDto> addAdministracion (@RequestBody EditAdministracionDto newAdministraction){

        Administracion a = administracionServicio.save(newAdministraction);

        return ResponseEntity
                .status(201)
                .body(GetAddAdministracionDto.of(a));
    }

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
                                                     "fecha": "19-10-2023",
                                                     "edadAlAdministrar": "10 meses",
                                                     "nota": "EDFWREREV",
                                                     "paciente": {
                                                         "id": 1,
                                                         "nombreCompleto": "Fran Ruiz",
                                                         "cantidadVacuna": 2
                                                     },
                                                     "vacunaIdNombreDto": {
                                                         "id": 1,
                                                         "nombre": "Alergia"
                                                     }
                                                 },
                                                 {
                                                     "id": 2,
                                                     "fecha": "19-11-2023",
                                                     "edadAlAdministrar": "10 meses",
                                                     "nota": "efweferf",
                                                     "paciente": {
                                                         "id": 2,
                                                         "nombreCompleto": "Paciente 1",
                                                         "cantidadVacuna": 1
                                                     },
                                                     "vacunaIdNombreDto": {
                                                         "id": 1,
                                                         "nombre": "Alergia"
                                                     }
                                                 },
                                                 {
                                                     "id": 3,
                                                     "fecha": "19-11-2023",
                                                     "edadAlAdministrar": "10 meses",
                                                     "nota": "creado",
                                                     "paciente": {
                                                         "id": 1,
                                                         "nombreCompleto": "Fran Ruiz",
                                                         "cantidadVacuna": 2
                                                     },
                                                     "vacunaIdNombreDto": {
                                                         "id": 1,
                                                         "nombre": "Alergia"
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
    public List<GetAdministracionDto> findAll(){

        return administracionServicio.findAll()
                        .stream()
                        .map(GetAdministracionDto::of)
                        .toList();
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
                                                  "id": 1,
                                                  "fecha": "19-10-2023",
                                                  "edadAlAdministrar": "10 meses",
                                                  "nota": "EDFWREREV",
                                                  "paciente": {
                                                      "id": 1,
                                                      "nombre": "Fran",
                                                      "apellidos": "Ruiz",
                                                      "nombreCompleto": "Fran Ruiz",
                                                      "cantidadVacuna": 1
                                                  },
                                                  "vacunaInfo": {
                                                      "id": 1,
                                                      "nombre": "Alergia",
                                                      "descripcion": "Alergia contra el polen y los ácaros",
                                                      "momentos": [
                                                          {
                                                              "id": 1,
                                                              "edad": "2 meses",
                                                              "tipoDosis": "Primera",
                                                              "Recomendaciones": "Reposo durante el día",
                                                              "discriminante": "T"
                                                          },
                                                          {
                                                              "id": 2,
                                                              "edad": "6 meses",
                                                              "tipoDosis": "Segunda",
                                                              "Recomendaciones": "Ponerse frío",
                                                              "discriminante": "H"
                                                          }
                                                      ]
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
    public GetAdministracionDto findById (@PathVariable Long id){

        Administracion a = administracionServicio.findById(id);

        return GetAdministracionDto.of(a);

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
    public GetPacienteDto findByIdPanciente(@PathVariable Long id){

        Paciente p = pacienteServicio.findById(id);

        return GetPacienteDto.find(p);
    }

    @Operation(summary = "Borra una administracion por su id")
    @ApiResponse(responseCode = "204 No Content",
            description = "Borrado con éxito",
            content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        administracionServicio.delete(id);

        return ResponseEntity.noContent().build();

    }
}
