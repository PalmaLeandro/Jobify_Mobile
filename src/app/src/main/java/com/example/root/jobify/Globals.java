package com.example.root.jobify;

import android.util.Log;

import com.example.root.jobify.Services.Persons.PersonService;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dani on 25/09/16.
 */
public class Globals {

    public static final int DATE_LONG_STYLE = 0;
    public static final int DATE_SHORT_STYLE = 1;

    public static Date parseDate(String dateString) {
        return parseDate(dateString, DATE_LONG_STYLE);
    }

    public static Date parseDate(String dateString, int style) {
        String format = (style == DATE_LONG_STYLE) ? "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'" : "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            Log.d("Date parsing", "The date didn have the format expected.");
        }
        return convertedDate;
    }

    public static String getImageURLFrom(JsonObject jsonObj) {
        return PersonService.API_URL + jsonObj.get("image").getAsJsonObject().get("url").getAsString();
    }

}
