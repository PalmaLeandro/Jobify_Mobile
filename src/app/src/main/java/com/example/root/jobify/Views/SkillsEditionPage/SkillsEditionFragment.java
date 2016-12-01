package com.example.root.jobify.Views.SkillsEditionPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.SkillsListPage.SkillsFragment;

/**
 * Created by root on 26/11/16.
 */
public class SkillsEditionFragment extends WoloxFragment<SkillsEditionPresenter>{

    private static final int MAX_LEGTH_SKILL = 40;
    private FloatingActionButton addSkillButton;
    SkillsFragment skillsFragment;

    @Override
    protected int layout() {
        return R.layout.skill_edition_page;
    }

    @Override
    protected void setUi(View v) {
        addSkillButton = (FloatingActionButton) v.findViewById(R.id.add_skill);
    }

    @Override
    protected void init() {
        skillsFragment = new SkillsFragment();
        skillsFragment.allowDeletion(true);
        skillsFragment.setPersonId(UserAuthService.getInstance().getUserProfile().getId());
        replaceFragment(R.id.skill_list,skillsFragment);
    }

    @Override
    protected void populate() {
    }

    @Override
    protected void setListeners() {
        final Context context = getContext();
        addSkillButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.add_skill_modal, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.skill_input);
                userInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_SKILL)});
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(R.string.add_button_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mPresenter.addSkill(userInput.getText().toString());
                                    }
                                })
                        .setNegativeButton(R.string.cancel_button_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

    @Override
    protected SkillsEditionPresenter createPresenter() {
        return new SkillsEditionPresenter(this);
    }
}
