package com.example.root.jobify.Services.People;

import com.example.root.jobify.Deserializers.ServerArrayResponse;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Message;
import com.example.root.jobify.Models.Person;

import com.example.root.jobify.Deserializers.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 16/09/16.
 */
public interface PersonAPIService {

    @GET("users")
    Call<ServerArrayResponse<Person>> allPerson(@Header("Token") final String token);

    @GET("users")
    Call<ServerArrayResponse<Person>> getPerson(@Query("username") final String personUsername, @Header("Token") final String token);

    @GET("users")
    Call<ServerArrayResponse<Person>> getRecommendedFolks(@Query("sort") final String order, @Query("filter") final String limit, @Header("Token") final String token);

    @GET("users")
    Call<ServerArrayResponse<Person>> searchFolks(@Query("sort") final String order, @Query("filter") final String limit, @Query("job_position") final String position, @Query("skill") final String skill, @Header("Token") final String token);

    @GET("users/contacts")
    Call<ServerArrayResponse<Person>> getMyPeople(@Header("Token") final String token);

    @HTTP(method = "DELETE", path = "skills", hasBody = true)
    Call<Void> removeProfileSkill(@Body final String skill, @Header("Token") final String token);

    @HTTP(method = "DELETE", path = "experiences", hasBody = true)
    Call<Void> removeProfileExperience(@Body final String experienceId, @Header("Token") final String token);

    @POST("skills")
    Call<Void> addProfileSkill(@Body final String skill, @Header("Token") final String token);

    @POST("experiences")
    Call<Void> addProfileExperience(@Body final Experience experience, @Header("Token") final String token);

    @POST("users/me")
    Call<Void> savePerson(@Body final Person person, @Header("Token") final String token);

    @GET("chats")
    Call<ServerArrayResponse<Person>> getMyChats(@Header("Token") final String token);

    @DELETE("chats")
    Call<Void> deleteChat(@Body final String personId, @Header("Token") final String token);

    @POST("message")
    Call<Void> sendMessage(@Body final String message, @Body final String username, @Header("Token") final String token);

    @HTTP(method = "DELETE", path = "messages", hasBody = true)
    Call<Void> deleteMessage(@Body final String messageId, @Header("Token") final String token);

    @GET("chats")
    Call<ServerArrayResponse<Message>> getChatMessages(@Query("user") final String personId, @Header("Token") final String token);

    @POST("users/contacts")
    Call<Void> addPerson(@Body final Person username, @Header("Token") final String token);

    @HTTP(method = "DELETE", path = "users/contacts", hasBody = true)
    Call<Void> removePerson(@Body final Person username, @Header("Token") final String token);

    @POST("users/recommend")
    Call<Void> recommendFolk(@Body final Person username, @Header("Token") final String token);

    @HTTP(method = "DELETE", path = "users/recommend", hasBody = true)
    Call<Void> unrecommendFolk(@Body final Person username, @Header("Token") final String token);
}
