package com.example.root.jobify.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by root on 24/09/16.
 */
public class Experience {
    private String company;
    private String description;
    private String position;
    private String duration;


    public Experience(String company, String description, String position, String duration) {
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

    public String getYears() {
        return duration;
    }

}
