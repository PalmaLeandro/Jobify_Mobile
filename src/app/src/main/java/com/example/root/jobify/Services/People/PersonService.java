package com.example.root.jobify.Services.People;

import com.example.root.jobify.Deserializers.PersonDeserializer;
import com.example.root.jobify.Deserializers.PersonsArrayDeserializer;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Message;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Services.ServerResponse;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 16/09/16.
 */
public class PersonService {

    public static final String API_URL = "http://192.168.43.25:8000/";

    private PersonAPIService apiService;
    private UserAuthService authService;

    public PersonService(){
        apiService = new Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .registerTypeAdapter(Person.class,new PersonDeserializer())
                                //.registerTypeAdapter(new TypeToken<ServerResponse<Person>>(){}.getType(), new PaginatedResponseDeserializer<Person>(Person.class,new PersonDeserializer()))
                                //.registerTypeAdapter(new TypeToken<ServerResponse<Experience>>(){}.getType(), new PaginatedResponseDeserializer<Experience>(Experience.class))
                                .registerTypeAdapter(PersonsArrayResponse.class,
                                        new PersonsArrayDeserializer())
                                .create()))
                .build()
                .create(PersonAPIService.class);
        authService = UserAuthService.getInstance();
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

    public void getRecomendedFolks(final Callback<ArrayList<Person>> callback) {
        apiService.getRecomendedFolks(authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerResponse<ArrayList<Person>> serverResponse = (ServerResponse<ArrayList<Person>>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(serverResponse.data));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void getMyPeople(final Callback<ArrayList<Person>> callback) {
        apiService.getMyPeople(authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerResponse<ArrayList<Person>> serverResponse = (ServerResponse<ArrayList<Person>>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(serverResponse.data));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public void getPersonSkills(final Callback callback){
        apiService.getPerson(authService.getUser().getEmail()).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                callback.onResponse(call,Response.success(response.body().getSkills()));
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public void removeProfileSkill(String skill,Callback callback) {
        apiService.removeProfileSkill(skill,authService.getToken()).enqueue(callback);
    }

    public void removeProfileExperience(String experienceId, Callback callback) {
        apiService.removeProfileSkill(experienceId,authService.getToken()).enqueue(callback);
    }

    public void addSkill(String skill, Callback callback) {
        apiService.addProfileSkill(skill,authService.getToken()).enqueue(callback);
    }

    public void addExperience(Experience experience, Callback callback) {
        apiService.addProfileExperience(experience,authService.getToken()).enqueue(callback);
    }

    public void savePerson(Person person, Callback callback) {
        apiService.savePerson(person,authService.getUser().getEmail(),authService.getToken()).enqueue(callback);
    }

    public void getMyChats(final Callback<ArrayList<Person>> callback) {
        apiService.getMyChats(authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerResponse<ArrayList<Person>> serverResponse = (ServerResponse<ArrayList<Person>>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(serverResponse.data));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void sendMessage(String message, String personId, Callback callback) {
        apiService.sendMessage(message,personId,authService.getToken()).enqueue(callback);
    }

    public void deleteMessage(String messageId, Callback callback) {
        apiService.deleteMessage(messageId,authService.getToken()).enqueue(callback);
    }

    public void deleteChat(String personId, Callback callback) {
        apiService.deleteChat(personId,authService.getToken()).enqueue(callback);
    }

    public void getChatMessages(String personId, final Callback callback) {
        apiService.getChatMessages(personId,authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerResponse<ArrayList<Person>> serverResponse = (ServerResponse<ArrayList<Person>>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(serverResponse.data));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}
