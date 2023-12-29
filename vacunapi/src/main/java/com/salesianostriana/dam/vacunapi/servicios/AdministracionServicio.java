package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.administracion.EditAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.exception.CalendarioException.CalendarioNotFoundException;
import com.salesianostriana.dam.vacunapi.exception.PacienteException.PacienteNotFoundExcepcion;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
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
    private final PacienteServicio pacienteServicio;
    private final CalendarioServicio calendarioServicio;

    public Administracion save (EditAdministracionDto nuevo){
        Administracion a = new Administracion();

        a.setFecha(nuevo.fecha());
        a.setEdadAlAdministrar(nuevo.edadAlAdministrar());
        a.setNotas(nuevo.notas());

        Optional<Paciente> paciente = Optional.ofNullable(pacienteServicio.findById(nuevo.idPaciente()));
        if (paciente.isPresent()){
            a.setPaciente(paciente.get());
        }else {
            throw new PacienteNotFoundExcepcion();
        }

        Optional<Calendario> calendario = calendarioServicio.findById(nuevo.idCalendario());
        if (calendario.isPresent()){
           a.setMomento(calendario.get());
        }else {
            throw new CalendarioNotFoundException();
        }

        return repositorio.save(a);
    }

    public List<Administracion> findAll(){

        return repositorio.findAll();
    }

    public Optional<Administracion> findById(Long id){
        return repositorio.findById(id);
    }

}
