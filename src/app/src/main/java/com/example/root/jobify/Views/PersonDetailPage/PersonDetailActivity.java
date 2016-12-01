package com.example.root.jobify.Views.PersonDetailPage;

import android.view.View;

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

    }
}
