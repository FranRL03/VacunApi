package com.salesianostriana.dam.vacunapi.shared.exception;

import com.salesianostriana.dam.vacunapi.shared.model.AppException;
import org.springframework.http.HttpStatus;

public class EntityExistException extends AppException {

    public EntityExistException(String entity) {
        super(HttpStatus.CONFLICT, entity + "is already exists");
    }

}
