package com.example.SB_SmartNetStorage.exception;

public class ErrorInDatabase extends RuntimeException{
    public ErrorInDatabase(String msg){
        super(msg);
    }
}
