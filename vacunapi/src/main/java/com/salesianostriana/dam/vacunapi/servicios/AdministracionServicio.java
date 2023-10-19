package com.salesianostriana.dam.vacunapi.servicios;

import com.salesianostriana.dam.vacunapi.dto.administracion.GetAdministracionDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.repositorios.AdministracionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministracionServicio {

    private final AdministracionRepositorio repositorio;

//    public Administracion save (GetAdministracionDto nuevo){
//
//        Administracion a = new Administracion();
//
//        a.setFecha(nuevo.fecha());
//        a.setEdadAlAdministrar(nuevo.edadAlAdministrar());
//        a.setNotas(nuevo.nota());
//
//        Optional<Paciente> paciente = pacienteServicio.findById(nuevo.id());
//        if(paciente.isPresent())
//            a.setPaciente(paciente.get());
//
//        a.getPaciente().getNombre();
//        a.getPaciente().getApellidos();
//        a.getPaciente().getVacunasAdministradas();
//
//        return repositorio.save(a);
//    }

}
