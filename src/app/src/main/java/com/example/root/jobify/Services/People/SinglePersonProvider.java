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
    public void addPerson(String personId, Callback callback) {

    }

    @Override
    public void removePerson(String personId, Callback callback) {

    }

    @Override
    public void recommendFolk(String personId, Callback callback) {

    }

    @Override
    public void unrecommendFolk(String personId, Callback callback) {

    }

    @Override
    public void engageChat(String personId, Callback callback) {

    }
}
