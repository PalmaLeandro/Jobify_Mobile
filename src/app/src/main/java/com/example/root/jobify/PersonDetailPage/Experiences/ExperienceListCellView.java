package com.example.root.jobify.PersonDetailPage.Experiences;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.jobify.R;
import com.squareup.picasso.Picasso;

/**
 * Created by root on 07/09/16.
 */
public class ExperienceListCellView {

    CardView mExperienceCardView;
    TextView mExperienceCompanyTextView;
    TextView mExperienceDescriptionTextView;
    TextView mExperiencePositionTextView;
    TextView mExperienceYearsTextView;

    public ExperienceListCellView(final View view) {
        mExperienceCardView= (CardView) view;        
        mExperienceCompanyTextView = (TextView) mExperienceCardView.findViewById(R.id.experience_company);
        mExperienceDescriptionTextView = (TextView) mExperienceCardView.findViewById(R.id.experience_description);
        mExperiencePositionTextView = (TextView) mExperienceCardView.findViewById(R.id.experience_position);
        mExperienceYearsTextView = (TextView) mExperienceCardView.findViewById(R.id.experience_years);
    }

    public void setExperienceCompany(String experienceCompany) {
        this.mExperienceCompanyTextView.setText( experienceCompany);
    }

    public void setExperiencePosition(String experiencePosition) {
        this.mExperiencePositionTextView.setText( experiencePosition);
    }

    public void setExperienceDescription(String experienceDescription) {
        this.mExperienceDescriptionTextView.setText( experienceDescription);
    }

    public void setExperienceYears(String experienceYears) {
        this.mExperienceYearsTextView.setText( experienceYears);
    }
}

