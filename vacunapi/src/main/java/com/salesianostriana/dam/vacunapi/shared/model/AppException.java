package com.salesianostriana.dam.vacunapi.shared.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public abstract class AppException extends ErrorResponseException {

    public AppException(HttpStatus status, String detail) {
        super(status, createProblemDetail(status, detail), null);
    }

    private static ProblemDetail createProblemDetail(HttpStatus status, String detail) {
        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle(status.getReasonPhrase());
        pd.setDetail(detail);
        return pd;
    }
}
