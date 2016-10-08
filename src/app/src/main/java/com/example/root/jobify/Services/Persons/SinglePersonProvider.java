package com.example.root.jobify.Services.Persons;

import com.example.root.jobify.Models.PersonProvider;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Services.PaginatedResponse;

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
    public void getPersonExperiences(String personId, final Callback callback) {
        new PersonService().getPersonExperiences(personId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                callback.onResponse(call, Response.success(response.body()));
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
