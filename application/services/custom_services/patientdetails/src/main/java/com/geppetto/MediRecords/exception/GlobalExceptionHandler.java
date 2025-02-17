package com.geppetto.MediRecords.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import org.springframework.validation.ObjectError;
import java.io.FileNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    String errorMsg = ex.getBindingResult().getAllErrors().stream()
    .map(ObjectError::getDefaultMessage)
    .collect(Collectors.joining(", "));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
    }

@ExceptionHandler(EntityNotFoundException.class)
public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
    log.error("Entity not found exception caught", e);
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(e.getMessage());
    }

@ExceptionHandler(FileNotFoundException.class)
public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException e) {
    log.error("File not found exception caught", e);
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
    }

    }
