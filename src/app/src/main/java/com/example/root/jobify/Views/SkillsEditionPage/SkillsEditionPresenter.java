package com.example.root.jobify.Views.SkillsEditionPage;

import android.widget.Toast;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.PersonService;
import com.example.root.jobify.Utilities.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 26/11/16.
 */
public class SkillsEditionPresenter extends BasePresenter<SkillsEditionFragment>{
    public void addSkill(final String skill) {
        new PersonService().addSkill(skill, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(getView().getContext(),R.string.added_skill_message,Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_add_skill_message,Toast.LENGTH_LONG);
            }
        });
    }
}
