package com.example.root.jobify.Models;

import retrofit2.Callback;

/**
 * Created by root on 24/09/16.
 */
public interface PersonProvider {
    void getPerson(final String personId, Callback callback);

    void getPersonExperiences(String personId, Callback callback);

}
