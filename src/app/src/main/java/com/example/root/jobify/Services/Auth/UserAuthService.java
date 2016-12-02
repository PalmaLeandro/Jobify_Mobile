package com.example.root.jobify.Services.Auth;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import com.cloudrail.si.interfaces.Profile;
import com.cloudrail.si.services.Facebook;
import com.cloudrail.si.types.DateOfBirth;
import com.example.root.jobify.Globals;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Deserializers.ServerResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 08/10/16.
 */
public class UserAuthService implements Authenticable {

    //    public static final String API_URL = "http://192.168.43.25:8000/";
    public static final String API_URL = "http://192.168.0.115:8000/";

    private AuthAPI authAPI;

    private static UserAuthService ourInstance = new UserAuthService();

    public static UserAuthService getInstance() {
        return ourInstance;
    }

    private UserAuthService() {
        listeners = new ArrayList<>();
        authAPI =new Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthAPI.class);
    }

    private Profile profile;
    private String token;
    private User user;
    private Person userProfile;
    private Context lastLoginContext;
    private List<UserAuthListener> listeners;

    public void loginWithFacebook(Context context){
        final String appClientId ="41c31ab8b911207a2f0f0a18fe8257a6";
        final String appClientSecret ="513042982225369";
        this.lastLoginContext = context;
        profile = new Facebook(context, appClientSecret, appClientId);
        new SocialLogin().execute(this);
    }

    public void loginWithCredentials(final String userEmail,final String userPassword){
        user = new User(userEmail,userPassword);
        new CredentialsLogin().execute(this);
    }



    public User getUser() {
        return user;
    }

    public void logout(){
        new Logout().execute(this);
    }

    public void addUserListener(UserAuthListener userAuthListener){
        listeners.add(userAuthListener);
    }

    private void notifyListeners(){
        for(UserAuthListener userAuthListener: listeners){
            userAuthListener.onUserChanged(this.user);
        }
    }

    public String getToken() {
        return token;
    }

    @Override
    public Profile getProfile() {
        return profile;
    }

    @Override
    public void onUserAuthenticated(String token) {
        this.token = token;
        this.user=null;
        this.userProfile =null;
        if (token!=null){
            this.user=null;
            this.userProfile =null;
            try {
                Response<ServerResponse<Person>> getUserResponse = authAPI
                        .getUserProfile(token)
                        .execute();
                this.user=new User(getUserResponse.body().data.getName(),getUserResponse.body().data.getEmail(),"");
                this.userProfile =getUserResponse.body().data;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        notifyListeners();
    }

    @Override
    public AuthAPI getAPI() {
        return authAPI;
    }

    public void signUpWithCredentials(String userName, String userEmail, String userPassword) {
        this.user = new User(userName,userEmail,userPassword);
        new CredentialsSignUp().execute(this);
    }

    public void signUpWithFacebook(Context context) {
        final String appClientId ="41c31ab8b911207a2f0f0a18fe8257a6";
        final String appClientSecret ="513042982225369";
        this.lastLoginContext = context;
        profile = new Facebook(context, appClientSecret, appClientId);
        new SocialSignUp().execute(this);
    }

    public Person getUserProfile() {
        return userProfile;
    }


    private static class CredentialsSignUp extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                final String userName = params[0].getUser().getName();
                final String userEmail = params[0].getUser().getEmail();
                final String userPassword = params[0].getUser().getPassword();
                final Response<ServerResponse<AuthAPI.Authentication>> authenticationResponse = params[0].getAPI()
                        .signUp(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP),new AuthAPI.SignUpData(userEmail, userName,null,null,null,null,null, Globals.defaultBase64Image))
                        .execute();
                params[0].onUserAuthenticated(authenticationResponse.body().data.getToken());
            } catch (Exception e) {
                e.printStackTrace();
                params[0].onUserAuthenticated(null);
            }
            return null;
        }
    }

    private static class SocialSignUp extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                params[0].getProfile().login();
                final String userEmail = params[0].getProfile().getEmail();
                final String userPassword = params[0].getProfile().getIdentifier();
                final String userName = params[0].getProfile().getFullName();
                final String userGender = params[0].getProfile().getGender();
                DateOfBirth  dateOfBirth= params[0].getProfile().getDateOfBirth();
                String userDateOfBirth = null;
                if (dateOfBirth!=null){
                    userDateOfBirth= dateOfBirth.getDay().toString()+"/"+dateOfBirth.getMonth().toString()+"/"+dateOfBirth.getYear().toString();
                }
                final String imageUrl = params[0].getProfile().getPictureURL();
                String userImage =null;
                if (imageUrl!=null){
                    userImage=new URL(imageUrl).openConnection().getInputStream().toString();
                }
                if (userImage!=null)
                    userImage=Globals.defaultBase64Image;
                final Response<ServerResponse<AuthAPI.Authentication>> authenticationResponse = params[0].getAPI()
                        .signUp(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP),new AuthAPI.SignUpData(userEmail, userName,userGender,userDateOfBirth,null,null,null,userImage))
                        .execute();
                params[0].onUserAuthenticated(authenticationResponse.body().data.getToken());
            } catch (Exception e){
                e.printStackTrace();
                params[0].onUserAuthenticated(null);
            }
            return null;
        }
    }


    private static class CredentialsLogin extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                final String userEmail = params[0].getUser().getEmail();
                final String userPassword = params[0].getUser().getPassword();
                final Response<ServerResponse<AuthAPI.Authentication>> authenticationResponse = params[0].getAPI()
                        .login(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP))
                        .execute();
                params[0].onUserAuthenticated(authenticationResponse.body().data.getToken());
            } catch (Exception e){
                e.printStackTrace();
                params[0].onUserAuthenticated(null);
            }
            return null;
        }
    }

    private static class SocialLogin extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                params[0].getProfile().login();
                final String userEmail = params[0].getProfile().getEmail();
                final String userPassword = params[0].getProfile().getIdentifier();
                final Response<ServerResponse<AuthAPI.Authentication>> authenticationResponse = params[0].getAPI()
                        .login(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP))
                        .execute();
                params[0].onUserAuthenticated(authenticationResponse.body().data.getToken());
            } catch (Exception e){
                e.printStackTrace();
                params[0].onUserAuthenticated(null);
            }
            return null;
        }
    }


    private static class Logout extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                params[0].getProfile().logout();
            } catch (Exception e){
                e.printStackTrace();
            }

            params[0].onUserAuthenticated(null);
            return null;
        }
    }
}