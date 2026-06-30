package com.salesianostriana.dam.vacunapi.shared.exception;

import com.salesianostriana.dam.vacunapi.shared.model.AppException;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends AppException {
    public AccessDeniedException(String message) {

        super(HttpStatus.UNAUTHORIZED, message);
    }
}
