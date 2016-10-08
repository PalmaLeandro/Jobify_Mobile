package com.example.root.jobify.Models;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by root on 17/09/16.
 */
public interface PersonsProvider {

    void getPersons(Callback callback);

}
