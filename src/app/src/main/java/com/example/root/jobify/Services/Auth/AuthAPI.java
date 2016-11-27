package com.example.root.jobify.Services.Auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by root on 16/09/16.
 */
public interface AuthAPI {

    @POST("login")
    @Headers("Auth: {base64Authetication}")
    Call<Authentication> login(@Header("base64Authetication") final String base64Authetication);

    @POST("signup")
    @Headers("Auth: {base64Authetication}")
    Call<Authentication> signUp(@Header("base64Authetication") final String base64Authetication, @Body SignUpData signUpData);

    @POST("users/me")
    @Headers("Auth: {base64Authetication}")
    Call<SignUpData> getUser(@Header("base64Authetication") final String base64Authetication);


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