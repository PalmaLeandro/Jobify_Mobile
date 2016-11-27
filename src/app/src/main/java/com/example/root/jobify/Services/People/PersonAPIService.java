package com.example.root.jobify.Services.People;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Message;
import com.example.root.jobify.Models.Person;

import com.example.root.jobify.Services.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 16/09/16.
 */
public interface PersonAPIService {

    @GET("users")
    @Headers("Auth: {base64Authetication}")
    Call<ServerResponse<Person>> allPerson(@Header("base64Authetication") final String base64Authetication);

    @GET("users")
    @Headers("Auth: {base64Authetication}")
    Call<Person> getPerson(@Query("username") final String personUsername);

    @GET("users")
    @Headers("Auth: {base64Authetication}")
    Call<ServerResponse<Person>> getRecomendedFolks(@Header("base64Authetication") final String base64Authetication);

    @GET("users")
    @Headers("Auth: {base64Authetication}")
    Call<ServerResponse<Person>> getMyPeople(@Header("base64Authetication") final String base64Authetication);

    @DELETE("skills")
    @Headers("Auth: {base64Authetication}")
    Call<Void> removeProfileSkill(@Body final String skill, @Header("base64Authetication") final String base64Authetication);

    @DELETE("experiences")
    @Headers("Auth: {base64Authetication}")
    Call<Void> removeProfileExperience(@Body final String experienceId, @Header("base64Authetication") final String base64Authetication);

    @POST("skills")
    @Headers("Auth: {base64Authetication}")
    Call<Void> addProfileSkill(@Body final String skill, @Header("base64Authetication") final String base64Authetication);

    @POST("experiences")
    @Headers("Auth: {base64Authetication}")
    Call<Void> addProfileExperience(@Body final Experience experience, @Header("base64Authetication") final String base64Authetication);

    @PATCH("users/{username}")
    @Headers("Auth: {base64Authetication}")
    Call<Void> savePerson(@Body final Person person, @Path("username") final String username, @Header("base64Authetication") final String base64Authetication);

    @GET("chats")
    @Headers("Auth: {base64Authetication}")
    Call<ServerResponse<Person>> getMyChats(@Header("base64Authetication") final String base64Authetication);

    @POST("message")
    @Headers("Auth: {base64Authetication}")
    Call<Void> sendMessage(@Body final String message, @Body final String username, @Header("base64Authetication") final String base64Authetication);

    @DELETE("messages")
    @Headers("Auth: {base64Authetication}")
    Call<Void> deleteMessage(@Body final String messageId, @Header("base64Authetication") final String base64Authetication);


    @DELETE("chats")
    @Headers("Auth: {base64Authetication}")
    Call<Void> deleteChat(@Body final String personId, @Header("base64Authetication") final String base64Authetication);


    @GET("chats/{personId}")
    @Headers("Auth: {base64Authetication}")
    Call<ServerResponse<Message>> getChatMessages(@Path("personId") final String personId, @Header("base64Authetication") final String base64Authetication);

}
