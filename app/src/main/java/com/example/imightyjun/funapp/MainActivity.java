package com.example.imightyjun.funapp;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try(RestClient client = new RestClient()) {
                            Log.d("HTTP RESPONSE", client.GET());
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }).start();

                //alertDialog.setMessage(response);
                alertDialog.show();

            }
        });


    }


}
