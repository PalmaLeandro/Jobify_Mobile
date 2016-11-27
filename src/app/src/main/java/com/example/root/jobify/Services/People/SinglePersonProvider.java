package com.example.root.jobify.Services.People;

import com.example.root.jobify.Models.PersonProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 24/09/16.
 */
public class SinglePersonProvider implements PersonProvider{

    @Override
    public void getPerson(String personId, Callback callback) {
        new PersonService().getPerson(personId,callback);
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
