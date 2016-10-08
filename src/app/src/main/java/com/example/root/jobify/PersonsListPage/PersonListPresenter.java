package com.example.root.jobify.PersonsListPage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.Persons.SinglePersonProvider;
import com.example.root.jobify.Utilities.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 07/09/16.
 */
public class PersonListPresenter extends BasePresenter<PersonListFragment> {

    List<Person> mPersons;

    public PersonListPresenter(PersonListFragment viewInstance) {
        super(viewInstance);
        mPersons = new ArrayList<>();
    }

    public void loadPersons() {
        RecyclerView personListView = (RecyclerView) getView().mRecyclerView;
        PersonListFragment.PersonListRecyclerViewAdapter personListViewAdapter = (PersonListFragment.PersonListRecyclerViewAdapter) personListView.getAdapter();
        for (Person person : mPersons){
            personListViewAdapter.mValues.add(person);
        }
        personListViewAdapter.notifyDataSetChanged();
    }

    public void setPersonsParam(final String personUsername) {

        new SinglePersonProvider().getPerson(personUsername,new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, final Response<Person> response) {
                mPersons = new ArrayList<Person>();
                mPersons.add(response.body());
                loadPersons();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("GET_PERSONS","There wasn't possible to fetch the requested persons",t);
            }
        });

    }
}
