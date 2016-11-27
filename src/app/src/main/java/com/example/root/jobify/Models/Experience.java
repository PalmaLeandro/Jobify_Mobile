package com.example.root.jobify.Models;

import com.example.root.jobify.Views.GenericContentListPage.Content;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import retrofit2.Callback;

/**
 * Created by root on 24/09/16.
 */
public class Experience implements Content{
    private String id;
    private String company;
    private String description;
    private String position;
    private String duration;


    public Experience(String id, String company, String description, String position, String duration) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.position = position;
        this.duration = duration;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return getCompany();
    }

    @Override
    public String getSubTitle() {
        return getPosition()+"\n"+getDescription()+"\n"+getDuration();
    }

    @Override
    public String getBase64Image() {
        return null;
    }
}
