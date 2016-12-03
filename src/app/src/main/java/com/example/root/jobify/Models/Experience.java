package com.example.root.jobify.Models;

import com.example.root.jobify.Views.GenericContentListPage.Content;

/**
 * Created by root on 24/09/16.
 */
public class Experience implements Content{
    private String company;
    private String description;
//    private JobPosition position;
    private String position;
    private String duration;


    public Experience( String company, String description, String position, String duration) {
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
        return getTitle()+getSubTitle();
    }

    @Override
    public String getTitle() {
        return getCompany();
    }

    @Override
    public String getSubTitle() {
        return getPosition()+"\n"+getDescription()+"\n"+getDuration();
    }

    public String getPicture() {
        return null;
    }
}
