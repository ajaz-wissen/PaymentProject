package com.ajaz.exceptions;


public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(){}
    public InsufficientBalanceException(String msg){
        super(msg);
    }
}
