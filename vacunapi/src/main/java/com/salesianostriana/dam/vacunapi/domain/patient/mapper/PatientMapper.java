package com.salesianostriana.dam.vacunapi.domain.patient.mapper;

import com.salesianostriana.dam.vacunapi.domain.patient.dto.PatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto toPatientDto(Patient patient);
}
