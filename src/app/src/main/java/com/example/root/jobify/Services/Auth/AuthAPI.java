package com.example.root.jobify.Services.Auth;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Deserializers.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by root on 16/09/16.
 */
public interface AuthAPI {

    @GET("login")
    Call<ServerResponse<Authentication>> login(@Header("Authorization") final String base64Authetication);

    @POST("signup")
    Call<ServerResponse<Authentication>> signUp(@Header("Authorization") final String base64Authetication, @Body SignUpData signUpData);

    @GET("users/me")
    Call<ServerResponse<Person>> getUserProfile(@Header("Token") final String base64Authetication);


    class Authentication {
        private final String token;

        private Authentication(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

    class SignUpData {
        final String email;
        final String name;
        final String gender;
        final String dob;
        final String nationality;
        final String city;
        final String profile;
        final String picture;

        public SignUpData(String email, String name, String gender, String dob, String nationality, String city, String profile, String picture) {
            this.email = email;
            this.name = name;
            this.gender = gender;
            this.dob = dob;
            this.nationality = nationality;
            this.city = city;
            this.profile = profile;
            this.picture = picture;
        }
    }
}