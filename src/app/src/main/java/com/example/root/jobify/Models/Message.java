package com.example.root.jobify.Models;

import com.example.root.jobify.Views.GenericContentListPage.Content;

/**
 * Created by root on 26/11/16.
 */
public class Message implements Content{


    private final String id;
    private final String author;
    private final String text;

    public Message(String id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
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
        return text;
    }

    @Override
    public String getBase64Image() {
        return null;
    }
}
