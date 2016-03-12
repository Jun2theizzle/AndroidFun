package com.example.imightyjun.funapp;

/**
 * Created by iMightyJun on 1/23/16.
 */
public class HttpPayload {

    public String url;
    public String method;
    public String payload;

    public HttpPayload(String _url, String _method){
        url = _url;
        method = _method;
    }
}

