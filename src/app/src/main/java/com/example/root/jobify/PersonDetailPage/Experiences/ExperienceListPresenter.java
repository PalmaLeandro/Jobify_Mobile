package com.example.root.jobify.PersonDetailPage.Experiences;

import android.support.v7.widget.RecyclerView;

import com.example.root.jobify.Models.Experience;
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
public class ExperienceListPresenter extends BasePresenter<ExperienceListFragment> {

    List<Experience> mExperiences;

    public ExperienceListPresenter(ExperienceListFragment viewInstance) {
        super(viewInstance);
        mExperiences = new ArrayList<>();
    }

    public void loadExperiences() {
        RecyclerView experienceListView = (RecyclerView) getView().mRecyclerView;
        ExperienceListFragment.ExperienceListRecyclerViewAdapter experienceListViewAdapter = (ExperienceListFragment.ExperienceListRecyclerViewAdapter) experienceListView.getAdapter();
        for (Experience experience : mExperiences){
            experienceListViewAdapter.mValues.add(experience);
        }
        experienceListViewAdapter.notifyDataSetChanged();
    }

    public void setPersonId(String personId) {
        new SinglePersonProvider().getPersonExperiences(personId, new Callback<List<Experience>>() {
            @Override
            public void onResponse(Call<List<Experience>> call, Response<List<Experience>> response) {
                mExperiences = response.body();
                loadExperiences();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
