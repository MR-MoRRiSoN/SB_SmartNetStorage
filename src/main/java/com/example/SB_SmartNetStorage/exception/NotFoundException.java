package com.example.SB_SmartNetStorage.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id){
        super("Could Not Found User With Id "+id);
    }
}
