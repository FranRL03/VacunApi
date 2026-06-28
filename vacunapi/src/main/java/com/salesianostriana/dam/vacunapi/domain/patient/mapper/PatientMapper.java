package com.salesianostriana.dam.vacunapi.domain.patient.mapper;

import com.salesianostriana.dam.vacunapi.domain.patient.dto.CreatePatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.dto.PatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Patient toEntity(CreatePatientDto dto);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.email", target = "email")
    PatientDto toDto (Patient patient);
}
