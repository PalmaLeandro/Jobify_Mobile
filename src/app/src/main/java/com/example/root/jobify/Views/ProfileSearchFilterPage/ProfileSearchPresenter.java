package com.example.root.jobify.Views.ProfileSearchFilterPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Utilities.BasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 09/11/16.
 */

public class ProfileSearchPresenter extends BasePresenter<ProfileSearchFragment> {

    private static final int MAX_LEGTH_SKILL = 140;
    private UserAuthService authService;

    public ProfileSearchPresenter(ProfileSearchFragment viewInstance) {
        super(viewInstance);
        authService = UserAuthService.getInstance();
    }

    public void searchFolks() {
        ProfileSearchResultFragment profileSearchResultFragment = new ProfileSearchResultFragment();
        profileSearchResultFragment.setFilters(getView().view.getSkillInputText(),getView().view.getPositionInputText());
        getView().replaceResultList(profileSearchResultFragment);
    }

    public void loadSkills(){
        new PeopleService().getSkills(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                final ArrayList<String> skillsToSelect = response.body();
                final EditText userInput = getView().view.skillInput;
                userInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_SKILL)});
                userInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText input = userInput;
                        if (input.getText().toString().length()==0 && skillsToSelect.size()>0){
                            final Context context = v.getContext();
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    context);

                            // set dialog message
                            alertDialogBuilder
                                    .setTitle(R.string.select_skill_string)
                                    .setItems(skillsToSelect.toArray(new String[]{}), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            input.setText(skillsToSelect.get(which));
                                        }
                                    })
                                    .setCancelable(true);

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_fetch_available_skills_string,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadJobPositions(){
        new PeopleService().getJobPositions(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                final ArrayList<String> jobPositionsToSelect = response.body();
                final EditText userInput = getView().view.positionInput;
                userInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_SKILL)});
                userInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText input = userInput;
                        if (input.getText().toString().length()==0 && jobPositionsToSelect.size()>0){
                            final Context context = v.getContext();
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    context);

                            // set dialog message
                            alertDialogBuilder
                                    .setTitle(R.string.select_job_position)
                                    .setItems(jobPositionsToSelect.toArray(new String[]{}), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            input.setText(jobPositionsToSelect.get(which));
                                        }
                                    })
                                    .setCancelable(true);

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.couldnt_fetch_available_skills_string,Toast.LENGTH_LONG).show();
            }
        });
    }

}
