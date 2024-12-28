package com.ajaz.exceptions;

public class CurrencyMismatchException extends Exception{
    public CurrencyMismatchException(){}
    public CurrencyMismatchException(String msg){
        super(msg);
    }
}
