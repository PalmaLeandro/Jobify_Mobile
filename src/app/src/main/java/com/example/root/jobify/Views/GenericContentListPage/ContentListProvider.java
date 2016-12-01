package com.example.root.jobify.Views.GenericContentListPage;

import com.example.root.jobify.Models.Person;

import java.util.ArrayList;

import retrofit2.Callback;

/**
 * Created by root on 22/11/16.
 */
public interface ContentListProvider {


    void getContents(Callback<ArrayList<Content>> callback);

}
