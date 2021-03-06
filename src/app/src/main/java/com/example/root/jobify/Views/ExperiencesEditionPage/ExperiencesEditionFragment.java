package com.example.root.jobify.Views.ExperiencesEditionPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.ExperiencesListPage.ExperienceListFragment;

import java.util.ArrayList;

/**
 * Created by root on 26/11/16.
 */
public class ExperiencesEditionFragment extends WoloxFragment<ExperiencesEditionPresenter>{

    private static final int MAX_LEGTH_EXPERIENCE = 40;
    private static final int MAX_LEGTH_JOB_POSITION = 40;
    private FloatingActionButton addExperienceButton;
    ExperienceListFragment experienceListFragment;
    private ArrayList<String> jobPositionsToSelect;

    @Override
    protected int layout() {
        return R.layout.experiences_edition_page;
    }

    @Override
    protected void setUi(View v) {
        addExperienceButton = (FloatingActionButton) v.findViewById(R.id.add_experience);
    }

    @Override
    protected void init() {
        experienceListFragment = new ExperienceListFragment();
        experienceListFragment.setPersonId(UserAuthService.getInstance().getUserProfile().getId());
        experienceListFragment.allowDeletion(true);
        replaceFragment(R.id.experiences_list,experienceListFragment);
    }

    @Override
    protected void populate() {
        mPresenter.getJobPositions();
        jobPositionsToSelect= new ArrayList<String>();
        jobPositionsToSelect.add("Developer");
        jobPositionsToSelect.add("Software Engineer");
        jobPositionsToSelect.add("Data Scientist");
    }


    @Override
    protected void setListeners() {
        final Context context = getContext();
        addExperienceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.add_experience_modal, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText experienceCompanyInput = (EditText) promptsView
                        .findViewById(R.id.experience_company_input);
                final EditText experiencePositionInput = (EditText) promptsView
                        .findViewById(R.id.experience_position_input);

                final EditText userInput = experiencePositionInput;
                userInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_JOB_POSITION)});
                userInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText input = experiencePositionInput;
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


                final EditText experienceDescriptionInput = (EditText) promptsView
                        .findViewById(R.id.experience_description_input);
                final EditText experienceDurationInput = (EditText) promptsView
                        .findViewById(R.id.experience_duration_input);
                experienceCompanyInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_EXPERIENCE)});
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(R.string.add_button_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mPresenter.addExperience(experienceCompanyInput.getText().toString(),
                                                experiencePositionInput.getText().toString(),
                                                experienceDescriptionInput.getText().toString(),
                                                experienceDurationInput.getText().toString());
                                    }
                                })
                        .setNegativeButton(R.string.cancel_button_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

    @Override
    protected ExperiencesEditionPresenter createPresenter() {
        return new ExperiencesEditionPresenter(this);
    }

    public void setJobPositionsToSelect(ArrayList<String> jobPositionsToSelect) {
        this.jobPositionsToSelect = jobPositionsToSelect;
    }
}
