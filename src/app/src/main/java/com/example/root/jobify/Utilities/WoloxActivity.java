package com.example.root.jobify.Utilities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.root.jobify.R;

public abstract class WoloxActivity extends FragmentActivity {

    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavView;
    protected LinearLayout mContentView;

    private void setContent() {
        setContentView(R.layout.app_activity_layout);
        mContentView= (LinearLayout) findViewById(R.id.content_view);
        View.inflate(this, layout(), mContentView);
    }
/*
    private void setupDrawerContent() {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent();
        if (handleArguments(getIntent().getExtras())) {
            //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            //mNavView = (NavigationView) findViewById(R.id.nav_view);
            init();
            setUi();
            populate();
            //setupDrawerContent();
            setListeners();
        } else {
            showToast(R.string.unknown_error);
            finish();
        }
    }

    protected abstract int layout();

    /**
     * Reads arguments sent as a Bundle and saves them as appropriate.
     *
     * @param args The bundle obtainable by the getExtras method of the intent.
     * @return true if arguments were read successfully, false otherwise.
     * Default implementation returns true.
     */
    protected boolean handleArguments(Bundle args) {
        return true;
    }

    /**
     * Loads the view elements for the activity
     */
    protected void setUi() {
        // Do nothing, override if needed!
    }

    /**
     * Initializes any variables that the activity needs
     */
    protected abstract void init();

    /**
     * Populates the view elements of the activity
     */
    protected void populate() {
        // Do nothing, override if needed!
    }

    /**
     * Sets the listeners for the views of the activity
     */
    protected void setListeners() {
        // Do nothing, override if needed!
    }

    protected void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    protected void replaceFragment(int resId, Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resId, f)
                .commit();
    }

    protected void replaceFragment(int resId, Fragment f, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resId, f, tag)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}