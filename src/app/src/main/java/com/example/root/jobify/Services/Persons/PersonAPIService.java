package com.example.root.jobify.Services.Persons;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Models.Experience;

import com.example.root.jobify.Services.PaginatedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 16/09/16.
 */
public interface PersonAPIService {

    @GET("users.json")
    Call<PaginatedResponse<Person>> allPersons();

    @GET("users")
    Call<Person> getPerson(@Query("username") final String personUsername);

}
