package com.example.root.jobify.Models;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

/**
 * Created by root on 06/09/16.
 */
public class Person {

    private final String username;
    private final String city;
    private final String dob;
    private final String email;
    private final String gender;
    private final String name;

    private final String nationality;
    private final ArrayList<Experience> previous_exp;
    private final String profile;
    private final ArrayList<String> skills;
    private String imageURL;

    public Person(String username, String city, String dob, String email, String gender, String name, String nationality, ArrayList<Experience> previous_exp, String profile, ArrayList<String> skills, String imageURL) {
        this.username = username;
        this.city = city;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.nationality = nationality;
        this.previous_exp = previous_exp;
        this.profile = profile;
        this.skills = skills;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public ArrayList<Experience> getPreviousExperience() {
        return previous_exp;
    }

    public String getProfile() {
        return profile;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public String getPosition() {
        if (previous_exp.size()>0)
            return previous_exp.get(0).getPosition();
        else
            return profile;
    }

    public String getUsername() {
        return username;
    }
}
