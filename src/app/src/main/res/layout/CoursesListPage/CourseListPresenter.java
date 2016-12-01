package com.example.root.ubacity.personsListPage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.root.ubacity.Models.person;
import com.example.root.ubacity.Models.Providers.personsProvider;
import com.example.root.ubacity.Utilities.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 07/09/16.
 */
public class personListPresenter extends BasePresenter<com.example.root.ubacity.personsListPage.personListFragment> {

    List<person> mpersons;
    personsProvider mService;

    public personListPresenter(com.example.root.ubacity.personsListPage.personListFragment viewInstance, personsProvider service) {
        super(viewInstance);
        mpersons = new ArrayList<>();
        mService = service;
    }

    public void loadpersons() {
        RecyclerView personListView = (RecyclerView) getView().mRecyclerView;
        com.example.root.ubacity.personsListPage.personListFragment.personListRecyclerViewAdapter personListViewAdapter = (com.example.root.ubacity.personsListPage.personListFragment.personListRecyclerViewAdapter) personListView.getAdapter();
        personListViewAdapter.mValues.clear();
        for (person person : mpersons){
            personListViewAdapter.mValues.add(person);
        }
        personListViewAdapter.notifyDataSetChanged();
    }

    public void fetchpersons() {
        mService.getpersons(new Callback<List<person>>() {
            @Override
            public void onResponse(Call<List<person>> call, Response<List<person>> response) {
                mpersons = response.body();
                loadpersons();
            }

            @Override
            public void onFailure(Call<List<person>> call, Throwable t) {
                Log.e("GET_personS","It wasn't possible to fetch the requested persons", t);
            }
        });

    }
}
