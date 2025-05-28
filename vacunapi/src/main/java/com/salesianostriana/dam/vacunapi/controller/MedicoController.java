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

    @GetMapping("/citas")
    public List<CitasFindAllDto> findAll (@AuthenticationPrincipal Medico m) {

        return medicoServicio.find(m.getId())
                .stream()
                .map(CitasFindAllDto::of)
                .toList();
    }
}
