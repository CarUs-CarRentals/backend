package com.carus.controllers.handlers;

import com.carus.controllers.exceptions.StandartError;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus requestStatus = HttpStatus.NOT_FOUND;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Entity not found");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }

    public ResponseEntity<StandartError> internalServerError(InternalServerErrorException e, HttpServletRequest request) {
        HttpStatus requestStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Internal server error has occurred");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }
}
