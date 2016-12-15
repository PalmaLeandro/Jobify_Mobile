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


    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        PersonDetailFragment personDetailFragment = new PersonDetailFragment();
        personDetailFragment.setPersonId(getIntent().getStringExtra(PersonDetailFragment.PERSON_USERNAME));
        replaceFragment(R.id.activity_container,personDetailFragment);
    }
}
