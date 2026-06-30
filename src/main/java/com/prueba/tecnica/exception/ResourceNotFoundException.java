package com.prueba.tecnica.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

}