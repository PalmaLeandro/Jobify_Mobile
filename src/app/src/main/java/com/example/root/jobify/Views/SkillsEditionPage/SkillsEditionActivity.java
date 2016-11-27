package com.example.root.jobify.Views.SkillsEditionPage;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

/**
 * Created by root on 26/11/16.
 */

public class SkillsEditionActivity extends WoloxActivity {
    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        SkillsEditionFragment skillsEditionFragment = new SkillsEditionFragment();
        replaceFragment(R.id.activity_container,skillsEditionFragment);
    }
}
