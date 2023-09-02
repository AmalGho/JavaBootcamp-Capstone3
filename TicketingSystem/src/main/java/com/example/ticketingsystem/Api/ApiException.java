package com.example.ticketingsystem.Api;

public class ApiException extends RuntimeException{
    public ApiException(String msg) {
        super(msg);
    }
}
