package com.salesianostriana.dam.vacunapi.domain.patient.dto;

import com.salesianostriana.dam.vacunapi.domain.patient.model.Patient;

import java.time.format.DateTimeFormatter;

public record PatientDto(

        String id,
        String name,
        String lastName,
        String phone,
        String birthday,
        String dni,
        String address
) {

    public static PatientDto of(Patient p) {
        return new PatientDto(
          p.getId().toString(),
          p.getName(),
          p.getLastName(),
          p.getPhone(),
          p.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
          p.getDni(),
          p.getAddress()
        );
    }
}
