package com.salesianostriana.dam.vacunapi.domain.doctor.mapper;

import com.salesianostriana.dam.vacunapi.domain.doctor.dto.DoctorDto;
import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.email", target = "email")
    DoctorDto toDto(Doctor doctor);
}
