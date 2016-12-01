package com.example.root.jobify.Models;

import com.example.root.jobify.Deserializers.ServerResponse;

import retrofit2.Callback;

/**
 * Created by root on 24/09/16.
 */
public interface PersonProvider {
    void getPerson(String personId, Callback<ServerResponse<Person>> callback);

    void addPerson(Person personId, Callback callback);

    void removePerson(Person personId, Callback callback);

    void recommendFolk(Person personId, Callback callback);

    void unrecommendFolk(Person personId, Callback callback);

    void engageChat(String personId, Callback callback);
}
