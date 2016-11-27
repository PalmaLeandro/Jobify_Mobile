package com.example.root.jobify.Utilities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.root.jobify.R;

public abstract class WoloxActivity extends AppCompatActivity {

    public static String ACTIVITY_APPBAR_TITLE_KEY = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        if (handleArguments(getIntent().getExtras())) {
            init();
            setUi();
            populate();
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

    public void showToast(int resId) {
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