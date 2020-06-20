package com.servicio.backend.exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String detail){
        super(detail);
    }
}
