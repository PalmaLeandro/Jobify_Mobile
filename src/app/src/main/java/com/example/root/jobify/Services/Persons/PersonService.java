package com.example.root.jobify.Services.Persons;

import com.example.root.jobify.Deserializers.PersonDeserializer;
import com.example.root.jobify.Deserializers.PersonsArrayDeserializer;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.PaginatedResponse;
import com.example.root.jobify.Services.PaginatedResponseDeserializer;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 16/09/16.
 */
public class PersonService {

    public static final String API_URL = "http://192.168.1.2:8080/";

    private PersonAPIService apiService;

    public PersonService(){
        apiService = new Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .registerTypeAdapter(Person.class,new PersonDeserializer())
                                //.registerTypeAdapter(new TypeToken<PaginatedResponse<Person>>(){}.getType(), new PaginatedResponseDeserializer<Person>(Person.class,new PersonDeserializer()))
                                //.registerTypeAdapter(new TypeToken<PaginatedResponse<Experience>>(){}.getType(), new PaginatedResponseDeserializer<Experience>(Experience.class))
                                .registerTypeAdapter(PersonsArrayResponse.class,
                                        new PersonsArrayDeserializer())
                                .create()))
                .build()
                .create(PersonAPIService.class);
    }

    public void allPersons(Callback callback){
        apiService.allPersons().enqueue(callback);
    }

    public void getPerson(final String personUsername, Callback callback){
        apiService.getPerson(personUsername).enqueue(callback);
    }

    public void getPersonExperiences(final String personUsername, final Callback callback){
        apiService.getPerson(personUsername).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                callback.onResponse(call,Response.success(response.body().getPreviousExperience()));
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

}
