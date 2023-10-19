package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;
import com.salesianostriana.dam.vacunapi.repositorios.AdministracionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministracionServicio {

    private final AdministracionRepositorio repositorio;

    public List<Administracion> findAll(){

        return repositorio.findAll();
    }

    public Optional<Administracion> findById(Long id){
        return repositorio.findById(id);
    }

}
