package com.salesianostriana.dam.vacunapi.shared.error;

import com.salesianostriana.dam.vacunapi.security.jwt.refresh.RefreshTokenException;
import com.salesianostriana.dam.vacunapi.shared.model.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {

        ProblemDetail pd =
                ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pd.setTitle("Validation error");
        pd.setDetail("One or more fields are invalid");

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getField(),
                        e -> e.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));

        pd.setProperty("errors", errors);

        return pd;
    }

    @ExceptionHandler(RefreshTokenException.class)
    public ProblemDetail handleRefreshToken(RefreshTokenException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        pd.setTitle("Refresh Token Error");
        pd.setDetail(ex.getMessage());
        return pd;
    }

    @ExceptionHandler(AppException.class)
    public ProblemDetail handleAppException(AppException ex) {
        return ex.getBody();
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex) {

        ProblemDetail pd =
                ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pd.setTitle("Internal Server Error");
        pd.setDetail("Unexpected error occurred");

        return pd;
    }
}