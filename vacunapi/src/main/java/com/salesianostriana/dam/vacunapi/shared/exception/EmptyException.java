package com.salesianostriana.dam.vacunapi.shared.exception;

import com.salesianostriana.dam.vacunapi.shared.model.AppException;
import org.springframework.http.HttpStatus;

public class EmptyException extends AppException {
    public EmptyException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
