package com.example.root.jobify.Services.People;

import com.example.root.jobify.Deserializers.PersonDeserializer;
import com.example.root.jobify.Deserializers.PersonsArrayDeserializer;
import com.example.root.jobify.Deserializers.ServerArrayResponse;
import com.example.root.jobify.Deserializers.ServerArrayResponseDeserializer;
import com.example.root.jobify.Globals;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Message;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
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

    private static final String RECOMMENDED_FILTER ="recommended_by" ;
    private static final String RECOMMENDED_MAX_FILTER = "10";

    private PersonAPIService getApi(){
        return new Retrofit
                .Builder()
                .baseUrl(Globals.getServerAddress())
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
    }

    private UserAuthService authService;

    public PeopleService(){
        authService = UserAuthService.getInstance();
    }

    public void getPerson(final String personUsername, final Callback callback){
        getApi().getPerson(personUsername,authService.getToken()).enqueue(new Callback<ServerArrayResponse<Person>>() {
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

    public void getRecommendedFolks(final Callback<ArrayList<Person>> callback) {
        getApi().getRecommendedFolks(RECOMMENDED_FILTER,RECOMMENDED_MAX_FILTER,authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(removeCurrentUserFromThisFolks(serverResponse.data)));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void getMyPeople(final Callback<ArrayList<Person>> callback) {
        getApi().getMyPeople(authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(removeCurrentUserFromThisFolks(serverResponse.data)));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


    public void getPersonSkills(final String personId,final Callback callback){
        getPerson(personId,new Callback<ServerResponse<Person>>() {
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


    public void removeProfileSkill(String somethingThatISaidThatIKnewAndIDont,Callback callback) {
        authService.getUserProfile().removeSkills(somethingThatISaidThatIKnewAndIDont);
        savePerson(authService.getUserProfile(),callback);
    }

    public void removeProfileExperience(String somethingThatImNotProudOf, Callback callback) {
        authService.getUserProfile().removeExperience(somethingThatImNotProudOf);
        savePerson(authService.getUserProfile(),callback);
    }

    public void addSkill(String somethingThatIKnow, Callback callback) {
        authService.getUserProfile().getSkills().add(somethingThatIKnow);
        savePerson(authService.getUserProfile(),callback);
    }

    public void addExperience(Experience somethingThatIDid, Callback callback) {
        authService.getUserProfile().getPreviousExperience().add(somethingThatIDid);
        savePerson(authService.getUserProfile(),callback);
    }

    public void savePerson(final Person person, final Callback callback) {
        person.setFirebaseToken(authService.getFirebaseToken());
        getApi().savePerson(person,authService.getToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                authService.setUserProfile(person);
                authService.setUser(new User(person.getName(),person.getEmail(),null));
                callback.onResponse(call,Response.success(response.body()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(null,t);
            }
        });
    }

    public void getMyChats(final Callback<ArrayList<Person>> callback) {
        getApi().getMyChats(authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
                if (serverResponse!=null && serverResponse.data!=null)
                    callback.onResponse(call, Response.success(removeCurrentUserFromThisFolks(serverResponse.data)));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void sendMessage(final String message, String personId, Callback callback) {
        getApi().sendMessage(new Message(null,authService.getUserProfile().getName(),message,null),personId,authService.getToken()).enqueue(callback);
    }

    public void deleteMessage(String messageId, Callback callback) {
        getApi().deleteMessage(messageId,authService.getToken()).enqueue(callback);
    }

    public void deleteChat(String personId, Callback callback) {
        getApi().deleteChat(personId,authService.getToken()).enqueue(callback);
    }

    public void getChatMessages(String personId, final Callback callback) {
        getApi().getChatMessages(personId,authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Message> serverResponse = (ServerArrayResponse<Message>) response.body();
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
        getApi().searchFolks(RECOMMENDED_FILTER,RECOMMENDED_MAX_FILTER,position, skill, authService.getToken()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ServerArrayResponse<Person> serverResponse = (ServerArrayResponse<Person>) response.body();
                if (serverResponse!=null && serverResponse.data!=null){
                    callback.onResponse(call, Response.success(removeCurrentUserFromThisFolks(serverResponse.data)));
                } else {
                    callback.onFailure(null,new Exception());
                }
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

    public void addPerson(Person aGreatPersonThatIWantToBeConnectedWith, Callback callback) {
        authService.getUserProfile().getMyPeople().add(aGreatPersonThatIWantToBeConnectedWith.getEmail());
        savePerson(authService.getUserProfile(),callback);
    }

    public void removePerson(Person someMadafakaToRemove, Callback callback) {
        authService.getUserProfile().removeFellowFromConacts(someMadafakaToRemove);
        savePerson(authService.getUserProfile(),callback);
    }

    public void recommendFolk(Person someFolkToRecommend, Callback callback) {
        someFolkToRecommend.getFellowsWhoRecommendMe().add(authService.getUserProfile().getEmail());
        getApi().recommendFolk(someFolkToRecommend,authService.getToken()).enqueue(callback);
    }

    public void unrecommendFolk(Person someJerk, Callback callback) {
        someJerk.removeFellowFromGuysWhomRecommendedMe(authService.getUserProfile());
        getApi().unrecommendFolk(someJerk,authService.getToken()).enqueue(callback);
    }

    public void getSkills(final Callback<ArrayList<String>> callback) {
        getApi().getSkills(authService.getToken()).enqueue(new Callback<ServerArrayResponse<String>>() {
            @Override
            public void onResponse(Call<ServerArrayResponse<String>> call, Response<ServerArrayResponse<String>> response) {
                if(response.body()!=null&& response.body().data!=null&& response.body().data.size()>0){
                    callback.onResponse(null,Response.success(response.body().data));
                } else {
                    callback.onFailure(null,new Exception("No skills available"));
                }
            }

            @Override
            public void onFailure(Call<ServerArrayResponse<String>> call, Throwable t) {
                callback.onFailure(null,new Exception("No skills available"));
            }
        });
    }

    public void getJobPositions(final Callback<ArrayList<String>> callback) {
        getApi().getJobPositions(authService.getToken()).enqueue(new Callback<ServerArrayResponse<String>>() {
            @Override
            public void onResponse(Call<ServerArrayResponse<String>> call, Response<ServerArrayResponse<String>> response) {
                if(response.body()!=null&& response.body().data!=null&& response.body().data.size()>0){
                    callback.onResponse(null,Response.success(response.body().data));
                } else {
                    callback.onFailure(null,new Exception("No job positions available"));
                }
            }

            @Override
            public void onFailure(Call<ServerArrayResponse<String>> call, Throwable t) {
                callback.onFailure(null,new Exception("No job positions available"));
            }
        });
    }

    private ArrayList<Person> removeCurrentUserFromThisFolks(ArrayList<Person> fetchedFolks){
        if(fetchedFolks!=null){
            for(Person someGuy : fetchedFolks){
                if(someGuy.getId().equals(authService.getUserProfile().getId())){
                    fetchedFolks.remove(someGuy);
                    break;
                }
            }
        }
        return fetchedFolks;
    }
}
