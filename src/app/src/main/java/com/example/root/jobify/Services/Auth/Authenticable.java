package com.example.root.jobify.Services.Auth;

import com.cloudrail.si.interfaces.Profile;

/**
 * Created by root on 08/10/16.
 */
public interface Authenticable {
    Profile getProfile();
    User getUser();
    void onUserAuthenticated(String token);
    AuthAPI getAPI();
    String getFirebaseToken();
}