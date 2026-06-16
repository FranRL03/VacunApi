package com.salesianostriana.dam.vacunapi.shared.exception;

import com.salesianostriana.dam.vacunapi.shared.model.AppException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AppException {

    public EntityNotFoundException(String entity, Object id) {
        super(HttpStatus.NOT_FOUND, entity + " with reference " + id + " was not found");
    }

}
