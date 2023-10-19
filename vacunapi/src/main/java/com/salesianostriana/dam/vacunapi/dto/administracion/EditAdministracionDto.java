package com.salesianostriana.dam.vacunapi.dto.administracion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.vacunapi.View.AdministracionView;
import com.salesianostriana.dam.vacunapi.dto.calendario.EditCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
import com.salesianostriana.dam.vacunapi.modelo.Administracion;
import com.salesianostriana.dam.vacunapi.modelo.Calendario;
import com.salesianostriana.dam.vacunapi.modelo.Paciente;
import com.salesianostriana.dam.vacunapi.modelo.Vacuna;

import java.time.LocalDate;

public record EditAdministracionDto(

//        @JsonView({AdministracionView.administracionCreate.class})
//        Long id,
//
//        @JsonView({AdministracionView.administracionCreate.class})
//        LocalDate fecha,
//
//        @JsonView({AdministracionView.administracionCreate.class})
//        int edadAlAdministrar,
//
//        @JsonView({AdministracionView.administracionCreate.class})
//        String notas,
//
//        @JsonView({AdministracionView.administracionCreate.class})
//        GetPacienteFindAll pacienteFindAll

        //GetCalendarioDto getCalendario
) {

//            public static EditAdministracionDto of (Administracion a){
//
//                Paciente p = a.getPaciente();
//
//                return new GetAdministracionDto(
//                                a.getId(),
//                                a.getFecha(),
//                                a.getEdadAlAdministrar(),
//                                a.getNotas(),
//                                GetPacienteFindAll.of(a.getPaciente())
//                );


}


