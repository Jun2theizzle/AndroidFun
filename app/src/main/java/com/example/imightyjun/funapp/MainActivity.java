package com.example.imightyjun.funapp;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("HttpResponse");
        Button button = (Button) findViewById(R.id.newButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HTTP CLIENT", "CLIENT STARTS");
                try {
                    new RestClient(new RestClient.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            Log.d("HTTP RESPONSE :", output);
                            alertDialog.setMessage(output);
                            alertDialog.show();
                        }
                    }).execute(new HttpPayload());
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                //alertDialog.setMessage(response);

            }
        });

        Button mangaButton = (Button) findViewById(R.id.mangaBtn);
        mangaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new MangaParser().execute();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });


    }



}
