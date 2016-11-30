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

    @GET("users")
    Call<ServerArrayResponse<Person>> getMyPeople(@Header("Token") final String token);

    @DELETE("skills")
    Call<Void> removeProfileSkill(@Body final String skill, @Header("Token") final String token);

    @DELETE("experiences")
    Call<Void> removeProfileExperience(@Body final String experienceId, @Header("Token") final String token);

    @POST("skills")
    Call<Void> addProfileSkill(@Body final String skill, @Header("Token") final String token);

    @POST("experiences")
    Call<Void> addProfileExperience(@Body final Experience experience, @Header("Token") final String token);

    @POST("users/me")
    Call<Void> savePerson(@Body final Person person, @Header("Token") final String token);

    @GET("chats")
    Call<ServerArrayResponse<Person>> getMyChats(@Header("Token") final String token);

    @POST("message")
    Call<Void> sendMessage(@Body final String message, @Body final String username, @Header("Token") final String token);

    @DELETE("messages")
    Call<Void> deleteMessage(@Body final String messageId, @Header("Token") final String token);


    @DELETE("chats")
    Call<Void> deleteChat(@Body final String personId, @Header("Token") final String token);


    @GET("chats/{personId}")
    Call<ServerArrayResponse<Message>> getChatMessages(@Path("personId") final String personId, @Header("Token") final String token);

}
