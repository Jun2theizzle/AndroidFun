package com.example.imightyjun.funapp.Models;

import com.example.imightyjun.funapp.Models.Chapter;

import java.util.ArrayList;

/**
 * Created by iMightyJun on 3/11/16.
 */
public class Manga {
    public String Name;
    public Chapter[] Chapters;


    public ArrayList<String> GetChapters(){
        ArrayList<String> chapters = new ArrayList<String>();
        for(int i = 0; i < Chapters.length; i++)
            chapters.add(Chapters[i].ChapterNumber);

        return chapters;
    }

    @Override
    public String toString(){
        String retval = Name;
        for(Chapter ch : Chapters){
            retval += " " + ch.ChapterNumber;
        }
        return retval;
    }
}

