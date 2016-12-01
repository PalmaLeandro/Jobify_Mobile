package com.example.root.jobify.Models;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Views.GenericContentListPage.ContentListProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by root on 12/09/16.
 */
public interface PersonListProvider extends ContentListProvider{

    void getPeople(Callback<ArrayList<Person>> callback);

}
