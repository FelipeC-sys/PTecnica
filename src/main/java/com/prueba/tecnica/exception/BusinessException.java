package com.prueba.tecnica.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String mensaje){
        super(mensaje);
    }

}