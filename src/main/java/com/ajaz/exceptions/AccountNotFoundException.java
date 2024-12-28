package com.ajaz.exceptions;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(){}

    public AccountNotFoundException(String msg){
        super(msg);
    }
}
