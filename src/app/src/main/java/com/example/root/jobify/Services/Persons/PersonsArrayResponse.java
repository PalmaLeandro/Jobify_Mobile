package com.example.root.jobify.Services.Persons;

import com.example.root.jobify.Models.Person;

import java.util.ArrayList;

/**
 * Created by dani on 24/09/16.
 */
public class PersonsArrayResponse {

    public ArrayList<Person> persons;

    public PersonsArrayResponse(ArrayList<Person> persons) {
        this.persons = persons;
    }

}
