package com.example.root.jobify.Models;

import com.example.root.jobify.Views.GenericContentListPage.Content;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 26/11/16.
 */
public class Message implements Content{


    private final String id;
    @SerializedName("auth")
    private final String author;
    private final String text;
    private final String time;

    public Message(String id, String author, String text, String time) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.time = time;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return author;
    }

    @Override
    public String getSubTitle() {
        return text+"\n"+time;
    }

    public String getPicture() {
        return null;
    }
}
