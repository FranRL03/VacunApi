package com.salesianostriana.dam.vacunapi.repositorios;

import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministracionRepositorio extends JpaRepository<Administracion, Long> {
}
