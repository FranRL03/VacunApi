package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.dto.GetMedicoDto;
import com.salesianostriana.dam.vacunapi.dto.usuario.GetUserDetailDto;
import com.salesianostriana.dam.vacunapi.dto.usuario.UserResponse;
import com.salesianostriana.dam.vacunapi.modelo.Doctor;
import com.salesianostriana.dam.vacunapi.modelo.Patient;
import com.salesianostriana.dam.vacunapi.servicios.DoctorService;
import com.salesianostriana.dam.vacunapi.servicios.UserService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medico")
public class DoctorController {

//    private final UserService userService;
//    private final DoctorService medicoServicio;
//
//
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Obtener Usuario Logeado", content = {
//                    @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = GetUserDetailDto.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                "id": "c0a801b2-8c0d-1503-818c-0d250f870003",
//                                                "nombre": "Francisco Claro",
//                                                "email": "fran@gmail.com",
//                                                "avatar": null,
//                                                "direccion": "c/Evangelista, 3",
//                                                "codPostal": "41011",
//                                                "poblacion": "Sevilla",
//                                                "puntos": 100,
//                                                "pedidos": [
//                                                    {
//                                                        "id": "c0a801b2-8c0d-1503-818c-0d25106b000c",
//                                                        "fecha": "2023-11-26T20:39:42.053019",
//                                                        "estadoPedido": "CONFIRMADO",
//                                                        "importeTotal": 6.3
//                                                    }
//                                                ]
//                                            }
//                                            """
//                            )}
//                    )}),
//            @ApiResponse(responseCode = "401", description = "No se encuentra Usuario loggeado", content = @Content)
//    })
//    @Operation(summary = "getLoggedUser", description = "Obtener el Usuario loggeado")
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/profile")
//    public ResponseEntity<GetMedicoDto> getLoggedUser(@AuthenticationPrincipal Doctor user) {
//        return ResponseEntity.ok(GetMedicoDto.of(medicoServicio.findById(user.getId())));
//    }
//
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Registrarme como Cliente", content = {
//                    @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                "id": "c0a84001-8c15-1042-818c-15828332000f",
//                                                "username": "luismai",
//                                                "avatar": "https://img.a.transfermarkt.technology/portrait/big/610461-1647594517.jpg?lm=1",
//                                                "fullName": "Luis Miguel",
//                                                "roles": [
//                                                    "CLIENTE"
//                                                ],
//                                                "createdAt": null
//                                            }
//                                            """
//                            )}
//                    )}),
//            @ApiResponse(responseCode = "400", description = "Dato introducido inválido", content = @Content)
//    })
//    @Operation(summary = "createUser", description = "Registrarme como Cliente")
//    @PostMapping("/register")
//    public ResponseEntity<UserResponse> createUserWithUserRole(@Valid @RequestBody CreatePacienteDto created) {
//        Patient paciente = userService.createUserWithUserRole(created);
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(UserResponse.fromUser(paciente));
//    }
}
