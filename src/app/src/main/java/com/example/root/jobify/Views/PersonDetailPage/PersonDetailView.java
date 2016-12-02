package com.example.root.jobify.Views.PersonDetailPage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.jobify.R;

import java.io.ByteArrayInputStream;

/**
 * Created by root on 07/09/16.
 */
public class PersonDetailView {

    private static String SessionDateFormat = "dd/MM/yyyy";
    private Context context;

    private ImageView mPersonImageView;
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
    FloatingActionButton mPersonActionFAB;

    public void showRecommendProfileButtton() {
        recommendProfileButtton.setVisibility(View.VISIBLE);
    }

    public void hideRecommendProfileButtton() {
        recommendProfileButtton.setVisibility(View.GONE);
    }

    public void showUnrecommendProfileButtton() {
        unrecommendProfileButtton.setVisibility(View.VISIBLE);
    }

    public void hideUnrecommendProfileButtton() {
        unrecommendProfileButtton.setVisibility(View.GONE);
    }

    Button recommendProfileButtton;
    Button unrecommendProfileButtton;
    Button engageChatButtton;

    public PersonDetailView(final View view) {
        this.context = view.getContext();
        this.mPersonImageView = (ImageView) view.findViewById(R.id.person_image);
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
        this.mPersonActionFAB = (FloatingActionButton) view.findViewById(R.id.fab);
        this.recommendProfileButtton=  (Button) view.findViewById(R.id.person_recommend_button);
        this.unrecommendProfileButtton =  (Button) view.findViewById(R.id.person_unrecommend_button);
        this.engageChatButtton=  (Button) view.findViewById(R.id.person_chat_button);
    }

    public void setPersonImageURL(String base64Image) {
        if(base64Image!=null){
            try {
                mPersonImageView.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(base64Image.getBytes(),Base64.DEFAULT))));
            }catch (Exception e){
                Toast.makeText(getContext(), R.string.couldn_load_profile_picture,Toast.LENGTH_LONG).show();
            }
        }
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

    public void setPersonName(String personName) {
        this.mPersonNameView.setTitle(personName);
    }

    public Context getContext() {
        return context;
    }

    public void setPersonInscriptionActionIcon(int resId) {
        this.mPersonActionFAB.setImageResource(resId);
    }
}

