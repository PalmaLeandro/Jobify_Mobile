package com.example.root.jobify.Views.ExperiencesEditionPage;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

/**
 * Created by root on 26/11/16.
 */

public class ExperiencesEditionActivity extends WoloxActivity {
    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        ExperiencesEditionFragment experiencesEditionFragment= new ExperiencesEditionFragment();
        replaceFragment(R.id.activity_container,experiencesEditionFragment);
    }
}
