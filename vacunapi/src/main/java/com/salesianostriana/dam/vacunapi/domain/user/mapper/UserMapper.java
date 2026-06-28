package com.salesianostriana.dam.vacunapi.domain.user.mapper;

import com.salesianostriana.dam.vacunapi.domain.doctor.dto.CreateDoctorDto;
import com.salesianostriana.dam.vacunapi.domain.patient.dto.CreatePatientDto;
import com.salesianostriana.dam.vacunapi.domain.patient.dto.PatientDto;
import com.salesianostriana.dam.vacunapi.domain.user.dto.CreateUserDto;
import com.salesianostriana.dam.vacunapi.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", expression = "java(dto.username())")
    @Mapping(target = "email", expression = "java(dto.email())")
    @Mapping(target = "password", expression = "java(dto.password())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastPasswordChangeAt", ignore = true)
    User toEntityUser(CreateUserDto dto);
}
