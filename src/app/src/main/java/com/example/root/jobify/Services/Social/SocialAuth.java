package com.example.root.jobify.Services.Social;

import android.content.Context;
import android.os.AsyncTask;

import com.cloudrail.si.interfaces.Profile;
import com.cloudrail.si.services.Facebook;
import com.cloudrail.si.services.GooglePlus;

/**
 * Created by root on 08/10/16.
 */
public class SocialAuth implements Authenticable {
    private static SocialAuth ourInstance = new SocialAuth();

    public static SocialAuth getInstance() {
        return ourInstance;
    }

    private SocialAuth() {
    }

    private Profile profile;
    private String token;
    private User user;

    public void loginWithFacebook(Context context){
        final String appClientId ="41c31ab8b911207a2f0f0a18fe8257a6";
        final String appClientSecret ="513042982225369";
        profile = new Facebook(context,appClientSecret,appClientId);
        new SocialLogin().execute(this);
    }

    public void loginWithGooglePlus(Context context){
        final String appClientId ="1Vl4g1xSxPZ2A4z_t3sHL9eb";
        final String appClientSecret ="938296915619-kpmgfkblggh7i4eqnv5pgpb0n2lahm5g.apps.googleusercontent.com";
        //final String appClientId ="srmYxA2hMOcgecTkw69B4ck2";
        //final String appClientSecret ="1003356524097-3of9si9hiqdkf116d90aa51g4ibkro9f.apps.googleusercontent.com";
        profile = new GooglePlus(context,appClientSecret,appClientId);
        new SocialLogin().execute(this);
    }

    public User getUser() {
        return user;
    }

    public void logout(){
        profile.logout();
    }

    public String getToken() {
        return token;
    }

    @Override
    public Profile getProfile() {
        return profile;
    }

    @Override
    public void onUserAuthenticated(String token, User user) {
        this.token = token;
        this.user=user;
    }


    private static class SocialLogin extends AsyncTask<Authenticable,Void,Void>{
        @Override
        protected Void doInBackground(Authenticable... params) {
            try {
                params[0].getProfile().login();
                //TODO: signin to our server
                params[0].onUserAuthenticated("",new User(params[0].getProfile().getFullName(),params[0].getProfile().getEmail()));
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }
}
