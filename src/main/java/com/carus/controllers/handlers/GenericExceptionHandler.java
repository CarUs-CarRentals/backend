package com.carus.controllers.handlers;

import com.carus.controllers.exceptions.StandartError;
import com.carus.services.exceptions.EntityAlreadyExistsException;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import com.carus.services.exceptions.TokenException;
import org.springframework.dao.DataIntegrityViolationException;
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
        HttpStatus requestStatus = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Entity not found");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<StandartError> internalServerError(InternalServerErrorException e, HttpServletRequest request) {
        HttpStatus requestStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Internal server error has occurred");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandartError> entityAlreadyExists(EntityAlreadyExistsException e, HttpServletRequest request) {
        HttpStatus requestStatus = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Duplicate key");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<StandartError> invalidToken(TokenException e, HttpServletRequest request) {
        HttpStatus requestStatus = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Invalid token");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandartError> integrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus requestStatus = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError();
        error.setTimestamp(Instant.now());
        error.setStatus(requestStatus.value());
        error.setError("Integrity violation");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(requestStatus).body(error);
    }
}
