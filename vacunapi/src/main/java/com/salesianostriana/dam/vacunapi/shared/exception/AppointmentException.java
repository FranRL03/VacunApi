package com.salesianostriana.dam.vacunapi.shared.exception;

import com.salesianostriana.dam.vacunapi.shared.model.AppException;
import org.springframework.http.HttpStatus;

public class AppointmentException extends AppException {
    public AppointmentException(String entity) {

        super(HttpStatus.BAD_REQUEST, entity);
    }
}
