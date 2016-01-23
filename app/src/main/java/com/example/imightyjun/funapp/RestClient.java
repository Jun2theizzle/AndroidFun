package com.example.imightyjun.funapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by iMightyJun on 12/28/15.
 */
@SuppressWarnings("deprecation")
public class RestClient implements Closeable {
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    public RestClient() {

    }

    public String GET() throws Exception{
        try {
            HttpGet httpGet = new HttpGet("http://httpbin.org/get");
            httpGet.addHeader("accept", "application/json");
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code :" + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String output;
            StringBuilder sb = new StringBuilder();
            while((output = br.readLine()) != null)
                sb.append(output + "\n");
            br.close();

            return sb.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void close() throws IOException {
        httpClient.getConnectionManager().shutdown();
    }


}
