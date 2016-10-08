package com.example.root.jobify.Utilities;

import android.support.v4.app.Fragment;

import com.example.root.jobify.R;

/**
 * Created by dani on 11/09/16.
 */

public abstract class SingleFragmentActivity extends WoloxActivity {

    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        String tag = getTag();
        if (tag == null) {
            replaceFragment(R.id.activity_container, createFragment());
        } else {
            replaceFragment(R.id.activity_container, createFragment(), tag);
        }
    }

    protected abstract Fragment createFragment();

    protected String getTag() {
        return null;
    }

}