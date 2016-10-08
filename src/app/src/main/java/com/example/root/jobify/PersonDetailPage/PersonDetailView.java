package com.example.root.jobify.PersonDetailPage;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.jobify.R;

import java.util.List;

/**
 * Created by root on 07/09/16.
 */
public class PersonDetailView {

    private static String SessionDateFormat = "dd/MM/yyyy";

    private View mPersonMainDetailView;
    //private ImageView mPersonImageView;
    private Button mPersonReviewsButton;
    private CollapsingToolbarLayout mPersonNameView;
    private CardView mPersonCityCardView;
    private TextView mPersonCityTextView;
    private CardView mPersonDobCardView;
    private TextView mPersonDobTextView;
    private CardView mPersonEmailCardView;
    private TextView mPersonEmailTextView;
    private CardView mPersonGenderCardView;
    private TextView mPersonGenderTextView;
    private CardView mPersonNationalityCardView;
    private TextView mPersonNationalityTextView;
    private CardView mPersonProfileCardView;
    private TextView mPersonProfileTextView;
    private CardView mPersonSkillsCardView;
    private TextView mPersonSkillsTextView;
    private FloatingActionButton mPersonAddFAB;

    public PersonDetailView(final View view) {
        this.mPersonMainDetailView = view;
        //this.mPersonImageView = (ImageView) view.findViewById(R.id.person_image);
        //this.mPersonReviewsButton = (Button) view.findViewById(R.id.person_rating);
        this.mPersonNameView =  (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        this.mPersonCityCardView =  (CardView) view.findViewById(R.id.person_city_card);
        this.mPersonCityTextView =  (TextView) view.findViewById(R.id.person_city);
        this.mPersonDobCardView =  (CardView) view.findViewById(R.id.person_dob_card);
        this.mPersonDobTextView =  (TextView) view.findViewById(R.id.person_dob);
        this.mPersonEmailCardView =  (CardView) view.findViewById(R.id.person_email_card);
        this.mPersonEmailTextView =  (TextView) view.findViewById(R.id.person_email);
        this.mPersonGenderCardView =  (CardView) view.findViewById(R.id.person_gender_card);
        this.mPersonGenderTextView =  (TextView) view.findViewById(R.id.person_gender);
        this.mPersonNationalityCardView =  (CardView) view.findViewById(R.id.person_nationality_card);
        this.mPersonNationalityTextView =  (TextView) view.findViewById(R.id.person_nationality);
        this.mPersonProfileCardView =  (CardView) view.findViewById(R.id.person_profile_card);
        this.mPersonProfileTextView =  (TextView) view.findViewById(R.id.person_profile);
        this.mPersonSkillsCardView =  (CardView) view.findViewById(R.id.person_skills_card);
        this.mPersonSkillsTextView =  (TextView) view.findViewById(R.id.person_skills);
        this.mPersonAddFAB = (FloatingActionButton) view.findViewById(R.id.fab);
        mPersonAddFAB .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Funcionalidad por implementar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setPersonImageURL(String imageURL) {
        //Picasso.with(mPersonMainDetailView.getContext()).load(imageURL).into(mPersonImageView);
    }

    public void setPersonCity(String personCity) {
        if (!personCity.equals("")){
            this.mPersonCityTextView.setText(personCity);
            this.mPersonCityCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonCityCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonDob(String personDob) {
        if (!personDob.equals("")){
            this.mPersonDobTextView.setText(personDob);
            this.mPersonDobCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonDobCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonEmail(String personEmail) {
        if (!personEmail.equals("")){
            this.mPersonEmailTextView.setText(personEmail);
            this.mPersonEmailCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonEmailCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonGender(String personGender) {
        if (!personGender.equals("")){
            this.mPersonGenderTextView.setText(personGender);
            this.mPersonGenderCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonGenderCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonNationality(String personNationality) {
        if (!personNationality.equals("")){
            this.mPersonNationalityTextView.setText(personNationality);
            this.mPersonNationalityCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonNationalityCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonProfile(String personProfile) {
        if (!personProfile.equals("")){
            this.mPersonProfileTextView.setText(personProfile);
            this.mPersonProfileCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonProfileCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonSkills(List<String> personSkills) {
        if (personSkills!=null && personSkills.size()>0){
            String text = "";
            for (String skill : personSkills){
                text+= skill +"\n";
            }
            this.mPersonSkillsTextView.setText(text);
            this.mPersonSkillsCardView.setVisibility(View.VISIBLE);
        } else {
            this.mPersonSkillsCardView.setVisibility(View.GONE);
        }
    }

    public void setPersonName(String personName) {
        this.mPersonNameView.setTitle(personName);
    }
}

