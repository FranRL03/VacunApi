package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByUsernameIgnoreCase(String username);

    Optional<Usuario> findFirstByUsername(String username);

    Optional<Usuario> findByEmail(String email);
}