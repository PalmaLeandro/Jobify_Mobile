package com.example.root.jobify.Services.Auth;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import com.cloudrail.si.interfaces.Profile;
import com.cloudrail.si.services.Facebook;
import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Person;

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

    public static final String API_URL = "http://192.168.43.25:8080/";

    private static UserAuthService ourInstance = new UserAuthService();

    public static UserAuthService getInstance() {
        return ourInstance;
    }

    private UserAuthService() {
        listeners = new ArrayList<>();
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
        if (token!=null){
            Response<AuthAPI.SignUpData> getUserResponse = null;
            try {
                getUserResponse = new Retrofit
                        .Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(AuthAPI.class)
                        .getUser(token)
                        .execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // TODO: update user profile
            this.user=new User("user name","user email","user password");
            ArrayList<Experience> previous_exp = new ArrayList<Experience>();
            previous_exp.add(new Experience("1", "google","barrer pisos","asistente de limpieza","2010 - Actualidad"));
            previous_exp.add(new Experience("1", "microsoft","barrer pasillos","asistente de limpieza","2009 - 2010"));
            ArrayList< String > skills = new ArrayList<>();
            skills.add("lavar platos");
            skills.add("barrer");
            skills.add("trapear");
            skills.add("lavar vidrios");
            this.userProfile = new Person("leopalma","san antonio de areco","25/10/1993","leandropalma0@gmail.com","male","Leandro Palma","Arg",previous_exp,"soy capo de la limpieza y tambien la muevo en la cocina",skills,null);

            notifyListeners();
        }
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
                final User user = params[0].getUser();
                final String userName = params[0].getUser().getName();
                final String userEmail = params[0].getUser().getEmail();
                final String userPassword = params[0].getUser().getPassword();
                final Response<AuthAPI.Authentication> authenticationResponse = new Retrofit
                        .Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(AuthAPI.class)
                        .signUp(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP),new AuthAPI.SignUpData(userEmail, userName,null,null,null,null,null,null))
                        .execute();
                //params[0].onUserAuthenticated(authenticationResponse.body().getToken()); TODO: make this work with tokens
                params[0].onUserAuthenticated(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP));
            } catch (Exception e){
                e.printStackTrace();
            }

            params[0].onUserAuthenticated("");
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
                final String userDateOfBirth = params[0].getProfile().getDateOfBirth().toString();
                final String userImage = new URL(params[0].getProfile().getPictureURL()).openConnection().getInputStream().toString();
                final Response<AuthAPI.Authentication> authenticationResponse = new Retrofit
                        .Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(AuthAPI.class)
                        .signUp(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP),new AuthAPI.SignUpData(userEmail, userName,userGender,userDateOfBirth,null,null,null,userImage))
                        .execute();
                //params[0].onUserAuthenticated(authenticationResponse.body().getToken()); TODO: make this work with tokens
                params[0].onUserAuthenticated(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP));
            } catch (Exception e){
                e.printStackTrace();
            }

            params[0].onUserAuthenticated("");
            return null;
        }
    }


    private static class CredentialsLogin extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                final User user = params[0].getUser();
                final String userEmail = params[0].getUser().getEmail();
                final String userPassword = params[0].getUser().getPassword();
                final Response<AuthAPI.Authentication> authenticationResponse = new Retrofit
                        .Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(AuthAPI.class)
                        .login(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP))
                        .execute();
                //params[0].onUserAuthenticated(authenticationResponse.body().getToken()); TODO: make this work with tokens
            } catch (Exception e){
                e.printStackTrace();
            }

            params[0].onUserAuthenticated("");
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
                final Response<AuthAPI.Authentication> authenticationResponse = new Retrofit
                        .Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(AuthAPI.class)
                        .login(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP))
                        .execute();
                //params[0].onUserAuthenticated(authenticationResponse.body().getToken()); TODO: make this work with tokens
                params[0].onUserAuthenticated(Base64.encodeToString(userEmail.concat(":").concat(userPassword).getBytes(),Base64.NO_WRAP));
            } catch (Exception e){
                e.printStackTrace();
            }

            params[0].onUserAuthenticated("");
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