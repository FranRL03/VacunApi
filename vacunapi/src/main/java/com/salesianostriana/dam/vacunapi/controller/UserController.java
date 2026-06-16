package com.salesianostriana.dam.vacunapi.controller;

import com.salesianostriana.dam.vacunapi.dto.usuario.*;
import com.salesianostriana.dam.vacunapi.modelo.Patient;
import com.salesianostriana.dam.vacunapi.modelo.User;
import com.salesianostriana.dam.vacunapi.security.jwt.access.JwtProvider;
import com.salesianostriana.dam.vacunapi.security.jwt.refresh.RefreshToken;
import com.salesianostriana.dam.vacunapi.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.vacunapi.security.jwt.refresh.RefreshTokenRequest;
import com.salesianostriana.dam.vacunapi.security.jwt.refresh.RefreshTokenService;
import com.salesianostriana.dam.vacunapi.servicios.PatientService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PatientService pacienteServicio;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Logeo", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = JwtUserResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "c0a84001-8c15-1cb9-818c-157d030f000e",
                                                "username": "luismi",
                                                "avatar": "https://images.ctfassets.net/86mn0qn5b7d0/featured-img-of-post-147629/b3ae746d78d5df9009780b0f72737d3a/featured-img-of-post-147629.jpg?fm=jpg&fl=progressive&q=50&w=1200&h=900&fit=fill",
                                                "fullName": "Luis Miguel",
                                                "roles": [
                                                    "CLIENTE"
                                                ],
                                                "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjMGE4NDAwMS04YzE1LTFjYjktODE4Yy0xNTdkMDMwZjAwMGUiLCJpYXQiOjE3MDExNjc1OTMsImV4cCI6MTcwMTI1Mzk5M30.gEitIh4OuXDBILSplw6IrAk0KU2sXFXMGmItsWWCtqpSiKY8x81WnI2aTwuXQ66RxIuml-uIU9_QcmhGFCKgUA"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401", description = "No se encuentra Usuario", content = @Content)
    })
    @Operation(summary = "login", description = "logeo")
    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(),
                                loginRequest.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();

        refreshTokenService.deleteByUser(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token, refreshToken.getToken()));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get( "refreshToken");

        refreshTokenService.deleteByToken(refreshToken);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Session closed successfully");
        return ResponseEntity.ok(response);
    }


    @PutMapping("/user/change-password")
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal User loggedUser) {

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getVerifyPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data Error");

        if (!userService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data Error");

        Optional<User> modified = userService.editPassword(loggedUser.getId(), changePasswordRequest.getNewPassword());

        if (modified.isPresent())
            return ResponseEntity.ok(UserResponse.fromUser(modified.get()));
        else
            throw new RuntimeException();
    }

    @PostMapping("/auth/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateToken(user);
                    refreshTokenService.deleteByUser(user);
                    RefreshToken refreshToken2 = refreshTokenService.createRefreshToken(user);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(JwtUserResponse.builder()
                                    .accessToken(token)
                                    .refreshToken(refreshToken2.getToken())
                                    .build());
                })
                .orElseThrow(() -> new RefreshTokenException("Refresh token not found"));

    }

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
//    public ResponseEntity<GetPacienteDto> getLoggedUser(@AuthenticationPrincipal Patient user) {
//            return ResponseEntity.ok(GetPacienteDto.of(pacienteServicio.findById(user.getId())));
//    }
//
//
//
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Editar datos del Cliente loggeado", content = {
//                    @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = GetPacienteDto.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                "id": "c0a801b2-8c0d-1417-818c-0d4421110003",
//                                                "nombre": "pepe",
//                                                "email": "pepe@gmail.com",
//                                                "avatar": "https://noticiasdelaciencia.com/upload/images/12_2021/6754_ciencia-en-imagenes-este-murcielago-da-la-cara.jpg",
//                                                "direccion": "C/Montaña nº3",
//                                                "codPostal": "33133",
//                                                "poblacion": "Valencia",
//                                                "puntos": 100,
//                                                "pedidos": [
//                                                    {
//                                                        "id": "c0a801b2-8c0d-1417-818c-0d4421e0000c",
//                                                        "fecha": "2023-11-26T21:13:38.144181",
//                                                        "estadoPedido": "CONFIRMADO",
//                                                        "importeTotal": 6.3
//                                                    }
//                                                ]
//                                            }
//                                            """
//                            )}
//                    )}),
//            @ApiResponse(responseCode = "400", description = "Dato introducido inválido", content = @Content)
//    })
//    @Operation(summary = "editLoggedUser", description = "Editar datos del Cliente loggeado")
//    @PutMapping("/profile/edit")
//    public GetPacienteDto editLoggedUser(@Valid @RequestBody EditLoggedUserDto editado, @AuthenticationPrincipal Patient p){
//        return GetPacienteDto.of(pacienteServicio.editLoggedUser(editado,p));
//    }


}
