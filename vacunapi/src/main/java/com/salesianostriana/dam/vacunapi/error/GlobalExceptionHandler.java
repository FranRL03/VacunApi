package com.salesianostriana.dam.vacunapi.error;

import com.salesianostriana.dam.vacunapi.exception.VacunaNotFoundExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(VacunaNotFoundExcepcion.class)
    ProblemDetail handleRutaNotFoundException (VacunaNotFoundExcepcion v) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, v.getMessage());
        problemDetail.setDetail("Ruta Not Found");
        problemDetail.setType(URI.create("https://vacuna.com/errors/not-found"));
        problemDetail.setProperty("timestamp",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        return problemDetail;
    }
}
