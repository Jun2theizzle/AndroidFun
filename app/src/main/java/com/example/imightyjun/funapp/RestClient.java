package com.example.imightyjun.funapp;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by iMightyJun on 12/28/15.
 */
@SuppressWarnings("deprecation")
public class RestClient extends AsyncTask<HttpPayload, Void, String> {
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
    protected String doInBackground(HttpPayload... params) {

        try {
            return ExecuteRequest(params[0]);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }


    public String ExecuteRequest(HttpPayload params) throws Exception{
        try {
            httpClient = new DefaultHttpClient();
            HttpResponse response;
            switch (params.method) {
                case "GET" :
                    HttpGet httpGet = new HttpGet(params.url);
                    httpGet.addHeader("accept", "text/html");
                    response = httpClient.execute(httpGet);
                    break;
                default:
                    return "none";
            }

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
