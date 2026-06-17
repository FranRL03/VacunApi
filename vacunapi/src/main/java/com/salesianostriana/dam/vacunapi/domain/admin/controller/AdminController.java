package com.salesianostriana.dam.vacunapi.domain.admin.controller;

import com.salesianostriana.dam.vacunapi.domain.admin.service.AdminService;
import com.salesianostriana.dam.vacunapi.domain.agenda.dto.AgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.dto.CreateAgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import com.salesianostriana.dam.vacunapi.domain.doctor.dto.CreateDoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.dto.DoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.patient.dto.CreatePatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.dto.PatientDto;
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

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Administrator Operations REST API")
public class AdminController {

    private final AdminService service;

    @Operation(summary = "Add patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Create doctor",
                    content = {@Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = Doctor.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": "c0a89694-9ed5-12f2-819e-d5e3e4fa0000",
                                                 "username": "DOCTOR03",
                                                 "name": "Juan",
                                                 "lastName": "García",
                                                 "email": "doctor3@example.com",
                                                 "speciality": "Dermatología",
                                                 "phone": "612345678"
                                             }
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "400",
                    description = "Error creating doctor",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody CreateDoctorDto newDoctor) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createDoctor(newDoctor));
    }

    @Operation(summary = "Create agenda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Create agenda",
                    content = {@Content(mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = DoctorAgenda.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                   "id": "c0a89694-9ed5-18ce-819e-d5e97faa0000",
                                                   "dayOfWeek": "Monday",
                                                   "startTime": "09:00:00",
                                                   "endTime": "14:00:00",
                                                   "duration": 20,
                                                   "active": true,
                                                   "doctorName": "DOCTOR"
                                               }
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "400",
                    description = "Error creating agenda",
                    content = @Content)
    })
    @PostMapping("/doctors/{doctorId}/agendas")
    public ResponseEntity<AgendaDto> addAAgenda(@PathVariable UUID doctorId, @RequestBody CreateAgendaDto agenda) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAgenda(agenda, doctorId));
    }
//
//    @Operation(summary = "Muestra una lista de los pacientes")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Lista de los pacientes que hay en la base de datos",
//                    content = {@Content(mediaType = "aplication/json",
//                            array = @ArraySchema(schema = @Schema(implementation = Patient.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            [
//                                                  {
//                                                      "id": 1,
//                                                      "nombre": "Fran",
//                                                      "apellidos": "Ruiz",
//                                                      "edad": "20 años",
//                                                      "cantidadVacuna": 1
//                                                  },
//                                                  {
//                                                      "id": 2,
//                                                      "nombre": "Paciente",
//                                                      "apellidos": "1",
//                                                      "edad": "5 meses",
//                                                      "cantidadVacuna": 1
//                                                  }
//                                              ]
//                                            """
//                            )}
//                    )}),
//
//            @ApiResponse(responseCode = "404",
//                    description = "Not found",
//                    content = @Content)
//    })
//    @GetMapping("/")
//    public List<GetPacienteFindAll> findAll() {
//
//        return pacienteServicio.findAll()
//                        .stream()
//                        .map(GetPacienteFindAll::of)
//                        .toList();
//
//    }
//
//    @Operation(summary = "Buscas un paciente por su id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Paciente por id",
//                    content = { @Content(mediaType = "aplication/json",
//                            array = @ArraySchema(schema = @Schema(implementation = Patient.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                  "id": 1,
//                                                  "nombre": "Fran",
//                                                  "apellidos": "Ruiz",
//                                                  "telefonoContacto": "987654321",
//                                                  "fechaNacimiento": "07-02-2003",
//                                                  "notas": "Este paciente está en tratamiento",
//                                                  "vacunasAdministradas": [
//                                                      {
//                                                          "id": 1,
//                                                          "fecha": "19-10-2023",
//                                                          "edadAlAdministrar": "10 meses",
//                                                          "vacuna": "Alergia",
//                                                          "tipoDosis": "Primera"
//                                                      }
//                                                  ]
//                                              }
//
//                                            """
//                            )}
//                    )}),
//
//            @ApiResponse(responseCode = "404",
//                    description = "Error al buscar un paciente",
//                    content = @Content)
//    })
//    @GetMapping("/{id}")
//    @JsonView(findByIdWithAllEntities.class)
//    public GetPacienteDto findById(@PathVariable UUID id){
//
//        Patient p = pacienteServicio.findById(id);
//
//        return GetPacienteDto.find(p);
//    }


}
