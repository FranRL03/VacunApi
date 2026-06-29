package com.salesianostriana.dam.vacunapi.domain.appointment.mapper;

import com.salesianostriana.dam.vacunapi.domain.appointment.dto.AppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.dto.CreateAppointmentDto;
import com.salesianostriana.dam.vacunapi.domain.appointment.modelo.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "doctor.name", target = "doctorName")
    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(source = "startDateTime", target = "startDateTime", qualifiedByName = "formatDate")
    @Mapping(source = "endDateTime", target = "endDateTime", qualifiedByName = "formatDate")
    AppointmentDto toDto (Appointment appointment);


    @Named("formatDate")
    default String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
