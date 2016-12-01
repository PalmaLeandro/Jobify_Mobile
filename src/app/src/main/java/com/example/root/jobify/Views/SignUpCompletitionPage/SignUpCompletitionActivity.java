package com.example.root.jobify.Views.SignUpCompletitionPage;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

/**
 * Created by root on 09/11/16.
 */

public class SignUpCompletitionActivity extends WoloxActivity {
    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_container,new SignUpCompletitionFragment());
    }
}
