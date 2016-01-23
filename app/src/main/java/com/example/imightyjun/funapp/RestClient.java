package com.example.imightyjun.funapp;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by iMightyJun on 12/28/15.
 */
public class RestClient {
    private HttpURLConnection connection = null;
    public String GET() {
        try {
            URL url = new URL("https://httpbin.org/get");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if(responseCode != 200) {
                throw new HttpException("Error : GET request");
            }

            connection.getContent();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }

        return "";
    }

}
