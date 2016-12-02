package com.example.root.jobify.Services.People;

import com.example.root.jobify.Deserializers.PersonDeserializer;
import com.example.root.jobify.Deserializers.PersonsArrayDeserializer;
import com.example.root.jobify.Deserializers.ServerArrayResponse;
import com.example.root.jobify.Deserializers.ServerArrayResponseDeserializer;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Deserializers.ServerResponse;
import com.example.root.jobify.Deserializers.ServerResponseDeserializer;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 16/09/16.
 */
public class PeopleService {

//    public static final String API_URL = "http://192.168.43.25:8000/";
    public static final String API_URL = "http://192.168.0.115:8000/";
    private static final String RECOMMENDED_FILTER ="recommended_by" ;
    private static final String RECOMMENDED_MAX_FILTER = "10";

    private PersonAPIService apiService;
    private UserAuthService authService;

    public PeopleService(){
        apiService = new Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .registerTypeAdapter(Person.class,new PersonDeserializer())
                                .registerTypeAdapter(PersonsArrayResponse.class,
                                        new PersonsArrayDeserializer())
                                .registerTypeAdapter(new TypeToken<ServerArrayResponse<Person>>(){}.getType(), new ServerArrayResponseDeserializer<Person>(Person.class,null))
                                .registerTypeAdapter(new TypeToken<ServerResponse<Person>>(){}.getType(), new ServerResponseDeserializer<Person>(Person.class,new PersonDeserializer()))
                                .registerTypeAdapter(new TypeToken<ServerResponse<Experience>>(){}.getType(), new ServerResponseDeserializer<Experience>(Experience.class))
                                .create()))
                .build()
                .create(PersonAPIService.class);
        authService = UserAuthService.getInstance();
    }

    public void getPerson(final String personUsername, final Callback callback){
        apiService.getPerson(personUsername,authService.getToken()).enqueue(new Callback<ServerArrayResponse<Person>>() {
            @Override
            public void onResponse(Call<ServerArrayResponse<Person>> call, Response<ServerArrayResponse<Person>> response) {
                if (response.body()!=null&&response.body().data!=null&&response.body().data.size()>=1){
                    boolean fetchedRigthFellow = false;
                    for (Person fetchedFellow : response.body().data){
                        if(fetchedFellow.getId().equals(personUsername)){
                            callback.onResponse(null,Response.success(new ServerResponse<Person>(fetchedFellow,"success")));
                            fetchedRigthFellow=true;
                            break;
                        }
                    }
                    if(!fetchedRigthFellow){
                        callback.onFailure(null,new Exception());
                    }
                }else {
                    callback.onFailure(null,new Exception());
                }
            }

            @Override
            public void onFailure(Call<ServerArrayResponse<Person>> call, Throwable t) {
                callback.onFailure(null,new Exception());
            }
        });
    }

    public void getPersonExperiences(final String personUsername, final Callback callback){
        getPerson(personUsername,new Callback<ServerResponse<Person>>() {
            @Override
            public void onResponse(Call<ServerResponse<Person>> call, Response<ServerResponse<Person>> response) {
                if (response.body()!=null&&response.body().data!=null){
                    callback.onResponse(call,Response.success(response.body().data.getPreviousExperience()));
                } else {
                    callback.onResponse(call,Response.success(new ArrayList<Experience>()));
                }

            }

            @Override
            public void onFailure(Call<ServerResponse<Person>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void getRecomendedFolks(final Callback<ArrayList<Person>> callback) {
        apiService.getRecommendedFolks(RECOMMENDED_FILTER,RECOMMENDED_MAX_FILTER,authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
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
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
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
        getPerson(authService.getUser().getEmail(),new Callback<ServerResponse<Person>>() {
            @Override
            public void onResponse(Call<ServerResponse<Person>> call, Response<ServerResponse<Person>> response) {
                if (response.body()!=null&&response.body().data!=null){
                    callback.onResponse(call,Response.success(response.body().data.getSkills()));
                } else {
                    callback.onResponse(call,Response.success(new ArrayList<String>()));
                }
            }

            @Override
            public void onFailure(Call<ServerResponse<Person>> call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public void removeProfileSkill(String skill,Callback callback) {
        authService.getUserProfile().removeSkills(skill);
        savePerson(authService.getUserProfile(),callback);
    }

    public void removeProfileExperience(String experienceId, Callback callback) {
        authService.getUserProfile().removeExperience(experienceId);
        savePerson(authService.getUserProfile(),callback);
    }

    public void addSkill(String skill, Callback callback) {
        authService.getUserProfile().getSkills().add(skill);
        savePerson(authService.getUserProfile(),callback);
    }

    public void addExperience(Experience experience, Callback callback) {
        authService.getUserProfile().getPreviousExperience().add(experience);
        savePerson(authService.getUserProfile(),callback);
    }

    public void savePerson(Person person, Callback callback) {
        apiService.savePerson(person,authService.getToken()).enqueue(callback);
    }

    public void getMyChats(final Callback<ArrayList<Person>> callback) {
        apiService.getMyChats(authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
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
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(serverResponse.data));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void searchFolks(final String skill, final String position, final Callback<ArrayList<Person>> callback) {
        apiService.searchFolks(null,null,position, skill, authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(serverResponse.data));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public boolean isAlreadyRecomendedByCurrentUser(Person notCurrentUserProfile){
        for(String personEmail: notCurrentUserProfile.getFellowsWhoRecommendMe()){
            if (personEmail.equals(authService.getUserProfile().getEmail()))
                return true;
        }
        return false;
    }

    public boolean userIsAlreadyAddedByCurrentUser(Person notCurrentUserProfile){
        for(String personEmail: authService.getUserProfile().getMyPeople()){
            if (personEmail.equals(notCurrentUserProfile.getEmail()))
                return true;
        }
        return false;
    }

    public void changeProfileAddition(Person mPerson) {
        boolean thatGuyWasAlreadyAddedToMyPeople = false;
        ArrayList<String> peopleOfCurrentUser = authService.getUserProfile().getMyPeople();
        for (String personEmail: peopleOfCurrentUser){
            if (personEmail.equals(mPerson.getEmail())){
                authService.getUserProfile().removeFellowFromConacts(mPerson);
                thatGuyWasAlreadyAddedToMyPeople = true;
                break;
            }
        }
        if(!thatGuyWasAlreadyAddedToMyPeople){
            authService.getUserProfile().getMyPeople().add(mPerson.getEmail());
        }
    }

    public void changeProfileRecomendation(Person mPerson) {
        boolean thatGuyWasAlreadyRecommendMe = false;
        ArrayList<String> peopleWhomAlreadyRecommendedThisFellow = mPerson.getFellowsWhoRecommendMe();
        for (String personEmail: peopleWhomAlreadyRecommendedThisFellow){
            if (personEmail.equals(mPerson.getEmail())){
                mPerson.removeFellowFromGuysWhomRecommendedMe(authService.getUserProfile());
                thatGuyWasAlreadyRecommendMe = true;
                break;
            }
        }
        if(!thatGuyWasAlreadyRecommendMe){
            mPerson.getFellowsWhoRecommendMe().add(authService.getUserProfile().getEmail());
        }
    }

    public void addPerson(Person personId, Callback callback) {
        apiService.addPerson(personId,authService.getToken()).enqueue(callback);
    }

    public void removePerson(Person personId, Callback callback) {
        apiService.removePerson(personId,authService.getToken()).enqueue(callback);
    }

    public void recommendFolk(Person personId, Callback callback) {
        apiService.recommendFolk(personId,authService.getToken()).enqueue(callback);
    }

    public void unrecommendFolk(Person personId, Callback callback) {
        apiService.unrecommendFolk(personId,authService.getToken()).enqueue(callback);
    }
}
