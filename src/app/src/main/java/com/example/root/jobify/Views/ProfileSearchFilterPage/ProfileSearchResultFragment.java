package com.example.root.jobify.Views.ProfileSearchFilterPage;

import android.widget.Toast;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Views.GenericContentListPage.Content;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 27/11/16.
 */

public class ProfileSearchResultFragment extends ContentListFragment {

    private String positionFilter;
    private String skillFilter;

    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PeopleService().searchFolks(skillFilter,positionFilter,
                        new Callback<ArrayList<Person>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                                ArrayList<Content> contents = new ArrayList<Content>();
                                for (Content content : response.body())
                                    contents.add(content);
                                callback.onResponse(null, Response.success(contents));
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                                Toast.makeText(getView().getContext(), R.string.search_result_error_string,Toast.LENGTH_LONG).show();
                                callback.onFailure(null,t);
                            }
                        });
            }
        };
    }

    public void setFilters(String skill, String position) {
        this.skillFilter = skill;
        this.positionFilter=position;
    }
}
