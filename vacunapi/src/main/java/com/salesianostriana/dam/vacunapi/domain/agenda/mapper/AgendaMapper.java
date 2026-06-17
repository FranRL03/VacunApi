package com.salesianostriana.dam.vacunapi.domain.agenda.mapper;

import com.salesianostriana.dam.vacunapi.domain.agenda.dto.AgendaDto;
import com.salesianostriana.dam.vacunapi.domain.agenda.model.DoctorAgenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(source = "dayOfWeek", target = "dayOfWeek")
    AgendaDto toDto(DoctorAgenda doctorAgenda);

    default String map (int dayOfWeek) {
        return switch (dayOfWeek) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            default -> "Unknown";
        };
    }
}
