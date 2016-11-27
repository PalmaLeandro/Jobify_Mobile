package com.example.root.ubacity.personsListPage;

import android.support.v7.widget.Toolbar;

import com.example.root.ubacity.R;
import com.example.root.ubacity.Utilities.WoloxActivity;

public abstract class personsListActivity<T extends com.example.root.ubacity.personsListPage.personListFragment> extends WoloxActivity {

    protected T personListFragment;


    @Override
    protected int layout() {
        return R.layout.persons_list_activity;
    }

    @Override
    protected void init() {
        personListFragment = createpersonListFragment();
        replaceFragment(R.id.person_list, personListFragment);

    }

    @Override
    protected void setUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_titles));
        String title = getIntent().getStringExtra(WoloxActivity.ACTIVITY_APPBAR_TITLE_KEY);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void populate() {
    }

    protected abstract T createpersonListFragment();

}
