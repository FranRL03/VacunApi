package com.salesianostriana.dam.vacunapi.domain.doctor.dto;

import com.salesianostriana.dam.vacunapi.domain.doctor.model.Doctor;
import com.salesianostriana.dam.vacunapi.domain.user.model.RolUser;

public record DoctorDto(

        String id,
        String username,
        String name,
        String lastName,
        String email,
        String speciality,
        String phone) {

    /*public static DoctorDto of (Doctor m){

        return new DoctorDto(
                m.getId().toString(),
                m.getUser().getUsername(),
                m.getName(),
                m.getLastName(),
                m.getUser().getEmail(),
                m.getPhone(),
                m.getSpeciality()
        );
    } */
}
