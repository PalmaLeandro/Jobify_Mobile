package com.example.root.jobify.Views.ExperiencesEditionPage;

import android.widget.Toast;

import com.example.root.jobify.Models.Experience;
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
public class ExperiencesEditionPresenter extends BasePresenter<ExperiencesEditionFragment>{

    public ExperiencesEditionPresenter(ExperiencesEditionFragment experiencesEditionFragment) {
        super(experiencesEditionFragment);
    }

    public void addExperience(final String experienceCompany, final String experiencePosition, final String experienceDescription, final String experienceDuratiopn) {
        new PeopleService().addExperience(new Experience(experienceCompany,experienceDescription,experiencePosition,experienceDuratiopn), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(getView().getContext(),R.string.added_skill_message,Toast.LENGTH_LONG).show();
                getView().experienceListFragment.populate();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_add_skill_message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getJobPositions() {
        new PeopleService().getJobPositions(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                getView().setJobPositionsToSelect(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_fetch_available_job_positions_string,Toast.LENGTH_LONG).show();
            }
        });
    }
}
