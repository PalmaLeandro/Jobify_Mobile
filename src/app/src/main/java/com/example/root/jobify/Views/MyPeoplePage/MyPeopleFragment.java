package com.example.root.jobify.Views.MyPeoplePage;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.GenericContentListPage.Content;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListProvider;
import com.example.root.jobify.Views.PersonDetailPage.PersonDetailActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 06/09/16.
 */
public class MyPeopleFragment extends ContentListFragment {

    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PeopleService().getMyPeople(new Callback<ArrayList<Person>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (Content content : response.body())
                            contents.add(content);
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                        callback.onFailure(null,t);
                    }
                });
            }
        };
    }

    @Override
    public Class<? extends WoloxActivity> getContentDetailActivity() {
        return PersonDetailActivity.class;
    }
}
