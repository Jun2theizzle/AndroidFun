package com.example.imightyjun.funapp;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

/**
 * Created by iMightyJun on 2/24/16.
 */
public class MangaParser extends AsyncTask<Void, Void, Elements> {

    private String[] mangaICareAbout = new String[] { "bleach", "haikyu", "nanatsu no taizai", "one piece" };

    @Override
    protected Elements doInBackground(Void... params){
        Document doc;
        Elements elements = null;
        try {
            doc = Jsoup.connect("http://www.mangatown.com/").get();
            elements = doc.select(".cartoon-list");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return elements;
    }

    @Override
    protected void onPostExecute(Elements result){
        Elements newReleases = result.first().select("li");
        Log.d("MangaTown New releases", Integer.toString(newReleases.size()));
        for(Element elem : newReleases){
            Element aTag = elem.select("a").first();
            Element pTag = elem.select("p > a").first();
            String mangaTitle = pTag.html();
            Log.d("Manga Title", mangaTitle);
            //Log.d("a tag rel", aTag.attr("rel").toLowerCase());
            if(Objects.equals(aTag.attr("rel").toLowerCase(), "magi"))
                Log.d("MAGI FOUND!!", aTag.toString());

        }


    }
}
