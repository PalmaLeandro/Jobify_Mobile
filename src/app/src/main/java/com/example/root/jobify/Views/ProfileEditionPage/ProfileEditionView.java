package com.example.root.jobify.Views.ProfileEditionPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.root.jobify.R;

/**
 * Created by root on 09/11/16.
 */

public class ProfileEditionView {
    private Context context;

    public ProfileEditionView (View view) {
        context=view.getContext();
        this.userNameInput = (EditText) view.findViewById(R.id.user_name_input);
        this.userCityInput = (EditText) view.findViewById(R.id.user_city_input);
        this.userDateOfBirthInput= (EditText) view.findViewById(R.id.user_dob_input);
        this.userGenderInput = (EditText) view.findViewById(R.id.user_gender_input);
        this.userNationalityInput = (EditText) view.findViewById(R.id.user_nationality_input);
        this.userProfileInput = (EditText) view.findViewById(R.id.user_profile_input);
        this.skillsButton= (Button) view.findViewById(R.id.skills_button_access);
        this.experiencesButton= (Button) view.findViewById(R.id.experiences_button_access);
        this.saveProfileButton= (FloatingActionButton) view.findViewById(R.id.save_profile);
        this.pickImageButton= (Button) view.findViewById(R.id.pick_image_button);
    }

    EditText userNameInput;
    EditText userCityInput;
    EditText userDateOfBirthInput;
    EditText userGenderInput;
    EditText userNationalityInput;
    EditText userProfileInput;
    Button skillsButton;
    Button experiencesButton;

    public Button getPickImageButton() {
        return pickImageButton;
    }

    Button pickImageButton;
    FloatingActionButton saveProfileButton;

    public final String getUserProfileInputText() {
        return userProfileInput.getText().toString();
    }

    public final String  getUserNationalityInputText() {
        return userNationalityInput.getText().toString();
    }

    public final String  getUserGenderInputText() {
        return userGenderInput.getText().toString();
    }

    public final String  getUserDateOfBirthInputText() {
        return userDateOfBirthInput.getText().toString();
    }

    public final String  getUserCityInputText() {
        return userCityInput.getText().toString();
    }

    public final String  getUserNameInputText() {
        return userNameInput.getText().toString();
    }

    public Context getContext() {
        return context;
    }


    public void setNumberOfExperiences(final Integer numberOfExperiences) {
        this.experiencesButton.setText(numberOfExperiences.toString()+" "+getContext().getText(R.string.experiences_button_text_string));
    }

    public void setNumberOfSkills(final Integer numberOfSkills) {
        this.skillsButton.setText(numberOfSkills.toString()+" "+getContext().getString(R.string.skills_button_text_string));
    }

    public void setUserProfileInputText(final String userProfile) {
        this.userProfileInput.setText(userProfile);
    }

    public void setUserNationalityInputText(final String userNationality) {
        this.userNationalityInput.setText(userNationality);
    }

    public void setUserGenderInputText(final String userGender) {
        this.userGenderInput.setText(userGender);
    }

    public void setUserDateOfBirthInputText(final String  userDateOfBirth) {
        this.userDateOfBirthInput.setText(userDateOfBirth);
    }

    public void setUserCityInputText(final String userCity) {
        this.userCityInput.setText(userCity);
    }

    public void setUserNameInputText(final String userName) {
        this.userNameInput.setText(userName);
    }

    private ProgressDialog progressDialog;

    public void showProgressDialog() {
        progressDialog =  ProgressDialog.show(context, "",
                "Ingresando. Por favor espere...", true);
    }

    public void hideProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
