package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.dto.citas.CitasFindAllDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.CreatePacienteDto;
import com.salesianostriana.dam.vacunapi.dto.usuario.UserResponse;
import com.salesianostriana.dam.vacunapi.modelo.Medico;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Usuario;
import com.salesianostriana.dam.vacunapi.servicios.MedicoServicio;
import com.salesianostriana.dam.vacunapi.servicios.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medico")
public class MedicoController {

    private final UsuarioService userService;
    private final MedicoServicio medicoServicio;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registrarme como Cliente", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "c0a84001-8c15-1042-818c-15828332000f",
                                                "username": "luismai",
                                                "avatar": "https://img.a.transfermarkt.technology/portrait/big/610461-1647594517.jpg?lm=1",
                                                "fullName": "Luis Miguel",
                                                "roles": [
                                                    "CLIENTE"
                                                ],
                                                "createdAt": null
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Dato introducido inválido", content = @Content)
    })
    @Operation(summary = "createUser", description = "Registrarme como Cliente")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUserWithUserRole(@Valid @RequestBody CreatePacienteDto created) {
        Paciente paciente = userService.createUserWithUserRole(created);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.fromUser(paciente));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listar las citas", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CitasFindAllDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": "d290f1ee-6c54-4b01-90e6-d701748f0851",
                                                    "dia": "15/06/2025",
                                                    "hora": "09:30",
                                                    "motivo": "RevisiÃ³n general",
                                                    "notas": "RevisiÃ³n anual del paciente",
                                                    "sala": "Sala 1",
                                                    "estado": "CONFIRMADA",
                                                    "idPaciente": "f5288a99-f910-4424-961d-d088a01f5ce0",
                                                    "idMedico": "1cef9086-93a1-49de-b5e7-fb3d01d44baa"
                                                },
                                                {
                                                    "id": "d290f1ee-6c54-4b01-90e6-d701748f0852",
                                                    "dia": "15/06/2025",
                                                    "hora": "10:30",
                                                    "motivo": "RevisiÃ³n general",
                                                    "notas": "RevisiÃ³n anual del paciente",
                                                    "sala": "Sala 1",
                                                    "estado": "CONFIRMADA",
                                                    "idPaciente": "f5288a99-f910-4424-961d-d088a01f5ce0",
                                                    "idMedico": "1cef9086-93a1-49de-b5e7-fb3d01d44baa"
                                                },
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400", description = "Dato introducido inválido", content = @Content)
    })
    @Operation(summary = "createUser", description = "Registrarme como Cliente")
    @GetMapping("/citas")
    public List<CitasFindAllDto> findAll (@AuthenticationPrincipal Medico m) {

        return medicoServicio.find(m.getId())
                .stream()
                .map(CitasFindAllDto::of)
                .toList();
    }
}
