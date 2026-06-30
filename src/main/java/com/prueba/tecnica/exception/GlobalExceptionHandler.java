package com.prueba.tecnica.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prueba.tecnica.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> recursoNoEncontrado(ResourceNotFoundException ex) {

        ApiResponse respuesta = new ApiResponse(false, ex.getMessage(), null);

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> errorNegocio(BusinessException ex) {

        ApiResponse respuesta = new ApiResponse(false, ex.getMessage(), null);

        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validar(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        ApiResponse respuesta = new ApiResponse(false, "Error de validación", errores);

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> general(Exception ex) {

        ApiResponse respuesta = new ApiResponse(false, "Error interno del servidor", null);

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}