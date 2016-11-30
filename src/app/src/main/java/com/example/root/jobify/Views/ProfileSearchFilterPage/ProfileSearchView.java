package com.example.root.jobify.Views.ProfileSearchFilterPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.jobify.R;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;

/**
 * Created by root on 09/11/16.
 */

public class ProfileSearchView {
    private View layout;
    private TextView resultsTitleTextView;

    public ProfileSearchView(View view) {
        layout=view;
        this.skillInput = (EditText) view.findViewById(R.id.skill_input);
        this.positionInput = (EditText) view.findViewById(R.id.position_input);
        this.searchButton = (FloatingActionButton) view.findViewById(R.id.search_button);
        this.resultsTitleTextView = (TextView) view.findViewById(R.id.results_title);
        this.resultsTitleTextView.setVisibility(View.INVISIBLE);
    }

    EditText skillInput;
    EditText positionInput;
    FloatingActionButton searchButton;


    public final String getPositionInputText() {
        return positionInput.getText().toString();
    }

    public final String getSkillInputText() {
        return skillInput.getText().toString();
    }

    public Context getContext() {
        return layout.getContext();
    }

    public void showResultsTitle() {
        this.resultsTitleTextView.setVisibility(View.VISIBLE);
    }
}
