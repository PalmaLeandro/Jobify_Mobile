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
import com.example.root.jobify.Views.SkillsListPage.SkillsFragment;

/**
 * Created by root on 26/11/16.
 */
public class ExperiencesEditionFragment extends WoloxFragment<ExperiencesEditionPresenter>{

    private static final int MAX_LEGTH_EXPERIENCE = 40;
    private FloatingActionButton addExperienceButton;
    ExperienceListFragment experienceListFragment;
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
        mPresenter=createPresenter();
        experienceListFragment.populate();
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
}
