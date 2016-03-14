package com.example.imightyjun.funapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.imightyjun.funapp.Models.HttpPayload;
import com.example.imightyjun.funapp.Models.Manga;
import com.example.imightyjun.funapp.Utilities.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetUp();

    }

    private void SetUp()
    {
        LoadMangaData();
        SetUpLoadDataBtn();
    }

    private void LoadMangaData(){
        final HttpPayload payload = new HttpPayload("https://www.imightybigman.com/manga", "GET");
        final Gson gson = new GsonBuilder().create();
        try {
            new RestClient(new RestClient.AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    Log.d("HTTP RESPONSE :", output);
                    CreateListView(gson.fromJson(output, Manga[].class));
                }
            }).execute(payload);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void SetUpLoadDataBtn(){
        Button button = (Button) findViewById(R.id.loadDataBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void CreateListView(Manga[] mangas){
        ListView listView = (ListView)findViewById(R.id.mangalist);
        ArrayAdapter<Manga> adapter = new ArrayAdapter<Manga>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mangas);
        listView.setAdapter(adapter);


    }



}
