package com.example.imightyjun.funapp;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.imightyjun.funapp.Models.HttpPayload;
import com.example.imightyjun.funapp.Models.Manga;
import com.example.imightyjun.funapp.Utilities.RestClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MangaReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_read);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config); // Get singleton instance
        Setup();
    }

    private void Setup(){
        GetImage();
    }

    private void GetImage(){
        //http://manga.imightybigman.com/MangaTown/Bleach/Chapter%20665/1.jpg
        final ImageView imageView = (ImageView) findViewById(R.id.MangaPage);

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.loadImage("http://manga.imightybigman.com/MangaTown/Bleach/Chapter%20665/1.jpg", new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                imageView.setImageBitmap(loadedImage);
            }
        });
    }

}
