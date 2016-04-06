package com.example.imightyjun.funapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.imightyjun.funapp.CustomAdapters.MangaExpandableListAdapter;
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
        payload.headers.put("accept", "application/json");
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
        Button button = (Button) findViewById(R.id.viewMangaBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMangaRead();
            }
        });
    }

    private void OpenMangaRead(){
        Intent intent = new Intent(this, MangaReadActivity.class);
        startActivity(intent);
    }

    private void CreateListView(Manga[] mangas){
        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.MangaExpandableList);
        expandableListView.setClickable(true);
        MangaExpandableListAdapter adapter = new MangaExpandableListAdapter(mangas);
        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableListView.setAdapter(adapter);

    }



}
