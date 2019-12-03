package com.buses.demo.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String detail){
        super(detail);
    }

}
