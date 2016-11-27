package com.example.root.ubacity.personsListPage;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.ubacity.R;
import com.squareup.picasso.Picasso;

/**
 * Created by root on 07/09/16.
 */
public class personListCellView {

    CardView mpersonCardView;
    ImageView mpersonImageImageView;
    TextView mpersonNameTextView;
    TextView mpersonDescriptionTextView;
    TextView mpersonRatingTextView;
    TextView mpersonFreshnessTextView;
    TextView mpersonOpeningTextView;

    public personListCellView(final View view) {
        mpersonCardView= (CardView) view;
        mpersonImageImageView = (ImageView) mpersonCardView.findViewById(R.id.person_image);
        mpersonNameTextView = (TextView) mpersonCardView.findViewById(R.id.person_name);
        mpersonDescriptionTextView = (TextView) mpersonCardView.findViewById(R.id.person_description);
        mpersonRatingTextView = (TextView) mpersonCardView.findViewById(R.id.person_rating);
        mpersonFreshnessTextView = (TextView) mpersonCardView.findViewById(R.id.person_freshness);
        mpersonOpeningTextView = (TextView) mpersonCardView.findViewById(R.id.person_opening);
    }

    public void setpersonName(final String name) {
        mpersonNameTextView.setText(name);
    }

    public void setpersonDescription(final String description) {
        mpersonDescriptionTextView.setText(description);
    }

    public void setpersonRating(final Double rating) {
        mpersonRatingTextView.setText(rating.toString().substring(0,3));
    }

    public void setpersonFreshness(final Boolean isNewperson) {
        if (isNewperson){
            mpersonFreshnessTextView.setBackgroundColor(mpersonCardView.getResources().getColor(R.color.new_person));
        } else {
            mpersonFreshnessTextView.setVisibility(View.INVISIBLE);
        }
    }

    public void setpersonOpenning(final Boolean curseWillBeOpenSoon) {
        if (curseWillBeOpenSoon){
            mpersonOpeningTextView.setBackgroundColor(mpersonCardView.getResources().getColor(R.color.starting_person));
        } else {
            mpersonOpeningTextView.setVisibility(View.INVISIBLE);
        }
    }

    public void setpersonImageURL(String imageURL) {
        Picasso.with(mpersonCardView.getContext()).load(imageURL).into(mpersonImageImageView);
    }
}

