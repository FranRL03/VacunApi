//package com.salesianostriana.dam.vacunapi.dto.administracion;
//
//import com.salesianostriana.dam.vacunapi.dto.calendario.GetCalendarioDto;
//import com.salesianostriana.dam.vacunapi.dto.paciente.GetPacienteFindAll;
//import com.salesianostriana.dam.vacunapi.modelo.Administracion;
//
//import com.salesianostriana.dam.vacunapi.modelo.Paciente;
//
//
//import java.time.LocalDate;
//
//public record EditAdministracionDto(
//
//        Long id,
//
//        LocalDate fecha,
//
//        int edadAlAdministrar,
//
//        String notas,
//
//        GetPacienteFindAll paciente,
//
//        GetCalendarioDto calendario
//
//
//
//) {
//
//    public static EditAdministracionDto of (Administracion a){
//
//        return new EditAdministracionDto(
//                a.getId(),
//                a.getFecha(),
//                a.getEdadAlAdministrar(),
//                a.getNotas(),
//                GetPacienteFindAll.of(a.getPaciente()),
//                GetCalendarioDto.of(a.getMomento())
//        );
//    }
//
//}
//
//
