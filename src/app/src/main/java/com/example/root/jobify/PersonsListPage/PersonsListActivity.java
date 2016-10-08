package com.example.root.jobify.PersonsListPage;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

public class PersonsListActivity extends WoloxActivity {

    public static final String PERSON_USERNAME = "person_username";

    private DrawerLayout mDrawerLayout;
    private PersonListFragment personListFragment;
    private Integer subcategoryID;


    @Override
    protected int layout() {
        return R.layout.main_persons_list_activity;
    }

    @Override
    protected void init() {
        personListFragment = new PersonListFragment();
        replaceFragment(R.id.person_list, personListFragment);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void populate() {
        super.populate();
        personListFragment.setUsername(getIntent().getStringExtra(PERSON_USERNAME));
    }
}
