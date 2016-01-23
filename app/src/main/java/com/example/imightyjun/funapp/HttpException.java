package com.example.imightyjun.funapp;

/**
 * Created by iMightyJun on 12/31/15.
 */
public class HttpException extends Exception {
    public HttpException(){

    }

    public HttpException(String message){
        super(message);
    }

    public HttpException(Throwable cause) { super(cause); }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
