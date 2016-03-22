package com.example.imightyjun.funapp.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imightyjun.funapp.Models.Manga;
import com.example.imightyjun.funapp.R;

import java.util.ArrayList;

/**
 * Created by iMightyJun on 3/12/16.
 */
public class MangaListAdapter extends ArrayAdapter<Manga> {
    Context context;
    int layoutResourceId;
    Manga[] mangas = null;

    public MangaListAdapter(Context context, int layoutResourceId, Manga[] mangas){
        super(context, layoutResourceId, mangas);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.mangas = mangas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        MangaHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new MangaHolder();
            holder.mangaTitle = (TextView)row.findViewById(R.id.MangaItemTitle);
            holder.mangaChapters = (ListView)row.findViewById(R.id.MangaItemChapters);
            row.setTag(holder);

        }
        else{
            holder = (MangaHolder)row.getTag();
        }
        Manga manga = mangas[position];
        ArrayList<String> mangaChapters = manga.GetChapters();
        holder.mangaTitle.setText(manga.Name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_list_item_1, android.R.id.text1, (String[])mangaChapters.toArray());
        holder.mangaChapters.setAdapter(adapter);
        return row;
    }

    static class MangaHolder{
        TextView mangaTitle;
        ListView mangaChapters;
    }

}
