package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.paciente.CreatePacienteDto;
import com.salesianostriana.dam.vacunapi.exception.UsuarioException.PasswordNotValidException;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.RolUsuario;
import com.salesianostriana.dam.vacunapi.modelo.Usuario;
import com.salesianostriana.dam.vacunapi.repositorios.PacienteRepositorio;
import com.salesianostriana.dam.vacunapi.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository userRepository;
    private final PacienteRepositorio pacienteRepository;

    public Paciente createUser(CreatePacienteDto created, EnumSet<RolUsuario> roles) {

        if (userRepository.existsByUsernameIgnoreCase(created.username()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");

        if(!created.password().equalsIgnoreCase(created.verifyPassword())){
            throw new PasswordNotValidException();
        }

        Paciente p = Paciente.builder()
                .username(created.username())
                .nombre(created.nombre())
                .apellidos(created.apellidos())
                .telefonoContacto(created.telefono())
                .password(passwordEncoder.encode(created.password()))
                .dni(created.dni())
                .direccion(created.direccion())
                .fechaNacimiento(created.fechaNacimiento())
                .email(created.email())
                .roles(Set.of(RolUsuario.PACIENTE))
                .build();

        return pacienteRepository.save(p);
    }

    public Paciente createUserWithUserRole(CreatePacienteDto created) {
        return createUser(created, EnumSet.of(RolUsuario.PACIENTE));
    }

    public String passwordGenerate() {

        String caracters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int longitud = 8;
        StringBuilder contrasena = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracters.length());
            contrasena.append(caracters.charAt(index));
        }

        return contrasena.toString();
    }
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    public Optional<Usuario> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    public Optional<Usuario> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<Usuario> edit(Usuario user) {

        // El username no se puede editar
        // La contraseña se edita en otro método

        return userRepository.findById(user.getId())
                .map(u -> {
                    return userRepository.save(u);
                }).or(() -> Optional.empty());

    }

    public Optional<Usuario> editPassword(UUID userId, String newPassword) {

        // Aquí no se realizan comprobaciones de seguridad. Tan solo se modifica

        return userRepository.findById(userId)
                .map(u -> {
                    u.setPassword(passwordEncoder.encode(newPassword));
                    return userRepository.save(u);
                });

    }

    public void delete(Usuario user) {
        deleteById(user.getId());
    }

    public void deleteById(UUID id) {
        // Prevenimos errores al intentar borrar algo que no existe
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
    }

    public boolean passwordMatch(Usuario user, String clearPassword) {
        return passwordEncoder.matches(clearPassword, user.getPassword());
    }



}
