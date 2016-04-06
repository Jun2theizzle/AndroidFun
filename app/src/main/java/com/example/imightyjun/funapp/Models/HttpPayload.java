package com.example.imightyjun.funapp.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iMightyJun on 1/23/16.
 */
public class HttpPayload {

    public String url;
    public String method;
    public String payload;
    public Map<String, String> headers;

    public HttpPayload(String _url, String _method){
        url = _url;
        method = _method;
        headers = new HashMap<String, String>();
    }
}

