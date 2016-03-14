package com.example.imightyjun.funapp.Models;

import com.example.imightyjun.funapp.Models.Chapter;

/**
 * Created by iMightyJun on 3/11/16.
 */
public class Manga {
    public String Name;
    public String Site;
    public Chapter[] Chapters;

    @Override
    public String toString(){
        String retval = Name;
        for(Chapter ch : Chapters){
            retval += " " + ch.ChapterNumber;
        }
        return retval;
    }
}

