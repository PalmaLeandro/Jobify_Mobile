package com.example.root.jobify.Services.People;

import com.example.root.jobify.Deserializers.ServerResponse;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Models.PersonProvider;

import retrofit2.Callback;

/**
 * Created by root on 24/09/16.
 */
public class SinglePersonProvider implements PersonProvider{

    @Override
    public void getPerson(String personId, Callback<ServerResponse<Person>> callback) {
        new PeopleService().getPerson(personId,callback);
    }


    @Override
    public void addPerson(Person personId, Callback callback) {
        new PeopleService().addPerson(personId,callback);
    }

    @Override
    public void removePerson(Person personId, Callback callback) {
        new PeopleService().removePerson(personId,callback);
    }

    @Override
    public void recommendFolk(Person personId, Callback callback) {
        new PeopleService().recommendFolk(personId,callback);
    }

    @Override
    public void unrecommendFolk(Person personId, Callback callback) {
        new PeopleService().unrecommendFolk(personId,callback);
    }

    @Override
    public void engageChat(String personId, Callback callback) {

    }
}
