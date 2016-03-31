package com.example.imightyjun.funapp.CustomAdapters;

import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imightyjun.funapp.Models.Manga;
import com.example.imightyjun.funapp.R;

import java.util.ArrayList;

/**
 * Created by iMightyJun on 3/21/16.
 */
public class MangaExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private Manga[] mangas;

    public MangaExpandableListAdapter(Manga[] mangas){
        this.mangas = mangas;
    }

    public void setInflater(LayoutInflater inflater, Activity activity){
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {

        return mangas.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mangas[groupPosition].Chapters.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.mangalist_chapters, null);
        }
        ViewGroup viewGroup = ((ViewGroup) convertView);
        TextView textView = (TextView)viewGroup.getChildAt(0);
        textView.setText(mangas[groupPosition].Name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.mangalist_item_row, null);
        }
        String mangaTitle = mangas[groupPosition].Name;
        String mangaChapter = mangas[groupPosition].Chapters[childPosition].ChapterNumber;
        String mangaLink = mangas[groupPosition].Chapters[childPosition].Link;

        textView = (TextView) convertView.findViewById(R.id.MangaItemTitle);

        String htmlLink = "<a href=\"" + mangaLink + "\">" + mangaTitle + " " + mangaChapter +"</a>";
        textView.setText(Html.fromHtml(htmlLink));
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
