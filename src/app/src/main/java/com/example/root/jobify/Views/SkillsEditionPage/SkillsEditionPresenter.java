package com.example.root.jobify.Views.SkillsEditionPage;

import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Utilities.BasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 26/11/16.
 */
public class SkillsEditionPresenter extends BasePresenter<SkillsEditionFragment>{
    public SkillsEditionPresenter(SkillsEditionFragment skillsEditionFragment) {
        super(skillsEditionFragment);
    }

    public void addSkill(final String skill) {
        new PeopleService().addSkill(skill, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(getView().getContext(),R.string.added_skill_message,Toast.LENGTH_LONG).show();
                getView().skillsFragment.populate();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_add_skill_message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getSkills() {
        new PeopleService().getSkills(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                getView().setSkillsToSelect(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_fetch_available_skills_string,Toast.LENGTH_LONG).show();
            }
        });
    }
}
