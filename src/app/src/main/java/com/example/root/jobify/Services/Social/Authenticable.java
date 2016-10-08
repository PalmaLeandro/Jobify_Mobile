package com.example.root.jobify.Services.Social;

import com.cloudrail.si.interfaces.Profile;

/**
 * Created by root on 08/10/16.
 */
public interface Authenticable {
    Profile getProfile();
    void onUserAuthenticated(String token, User user);
}