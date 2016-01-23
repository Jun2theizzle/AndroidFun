package com.example.imightyjun.funapp;

import android.os.AsyncTask;

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
public class RestClient extends AsyncTask<String, Void, String> {
    private DefaultHttpClient httpClient;
    public RestClient(AsyncResponse delegate) {
        this.delegate = delegate;
    }
    public interface AsyncResponse {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;

    @Override
    protected void onPostExecute(String result){
        delegate.processFinish(result);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            return GET();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }


    public String GET() throws Exception{
        try {
            httpClient = new DefaultHttpClient();
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
            Close();
            return sb.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void Close() throws IOException {
        httpClient.getConnectionManager().shutdown();
    }


}
