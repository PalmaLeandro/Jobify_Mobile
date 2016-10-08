package com.example.root.jobify.PersonDetailPage;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListFragment;
import com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListPresenter;
import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

/**
 * Created by root on 23/09/16.
 */
public class PersonDetailActivity extends WoloxActivity{

    PersonDetailView personDetailView;
    ExperienceListFragment experienceListFragment;

    public static final String PERSON_USERNAME= "person_id";

    @Override
    protected int layout() {
        return R.layout.main_person_detail_activity;
    }

    @Override
    protected void init() {
        personDetailView = new PersonDetailView(findViewById(R.id.main_person_detail_layout));
        experienceListFragment = new ExperienceListFragment();
        replaceFragment(R.id.person_experiences_fragment,experienceListFragment);

    }

    @Override
    protected void populate() {
        new PersonDetailPresenter(personDetailView).setPersonId(getIntent().getStringExtra(PERSON_USERNAME));
        new ExperienceListPresenter(experienceListFragment).setPersonId(getIntent().getStringExtra(PERSON_USERNAME));
    }
}
