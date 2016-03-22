package com.example.imightyjun.funapp.CustomAdapters;

import android.app.Activity;
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
    private ArrayList<String> parentItems, child;
    private ArrayList<Object> childItems;

    public MangaExpandableListAdapter(Manga[] mangas){
        parentItems = new ArrayList<String>();
        childItems = new ArrayList<Object>();
        for(Manga m : mangas){
            parentItems.add(m.Name);
            childItems.add(m.GetChapters());
        }
    }

    public void setInflater(LayoutInflater inflater, Activity activity){
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childItems.get(groupPosition)).size();
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
        ((TextView) convertView).setText(parentItems.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        child = (ArrayList<String>) childItems.get(groupPosition);
        TextView textView = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.mangalist_item_row, null);
        }
        textView = (TextView) convertView.findViewById(R.id.MangaItemTitle);
        textView.setText(child.get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
