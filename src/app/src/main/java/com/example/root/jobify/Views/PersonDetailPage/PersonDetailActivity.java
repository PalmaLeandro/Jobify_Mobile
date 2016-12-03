package com.example.root.jobify.Views.PersonDetailPage;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.ExperiencesListPage.ExperienceListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.SkillsListPage.SkillsFragment;

/**
 * Created by root on 23/09/16.
 */
public class PersonDetailActivity extends WoloxActivity{

    private static final int MAX_LEGTH_MSG = 140;
    PersonDetailPresenter mPresenter;
    PersonDetailView personDetailView;
    ExperienceListFragment experienceListFragment;

    public static final String PERSON_USERNAME= ContentListFragment.CONTENT_ID;

    @Override
    protected int layout() {
        return R.layout.main_person_detail_activity;
    }

    @Override
    protected void init() {
        final String fellowId = getIntent().getStringExtra(PERSON_USERNAME);
        personDetailView = new PersonDetailView(findViewById(R.id.main_person_detail_layout));
        SkillsFragment skillsFragment= new SkillsFragment();
        skillsFragment.setPersonId(fellowId);
        skillsFragment.allowDeletion(false);
        replaceFragment(R.id.person_skills_fragment,skillsFragment);
        experienceListFragment = new ExperienceListFragment();
        experienceListFragment.setPersonId(fellowId);
        experienceListFragment.allowDeletion(false);
        replaceFragment(R.id.person_experiences_fragment,experienceListFragment);
    }

    @Override
    protected void populate() {
        mPresenter = new PersonDetailPresenter(personDetailView);
        mPresenter.setPersonId(getIntent().getStringExtra(PERSON_USERNAME));
    }

    @Override
    protected void setListeners() {
        personDetailView.recommendProfileButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.recommendFolk();
            }
        });
        personDetailView.unrecommendProfileButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.unrecommendFolk();
            }
        });
        personDetailView.engageChatButtton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(arg0.getContext());
                View promptsView = li.inflate(R.layout.send_message_modal, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        arg0.getContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.skill_input);
                userInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_MSG)});
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(R.string.send_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mPresenter.sendMessage(userInput.getText().toString());
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
}
