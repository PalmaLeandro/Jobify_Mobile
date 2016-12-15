package com.example.root.jobify.Views.ProfileEditionPage;

import android.widget.Toast;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Utilities.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 09/11/16.
 */

public class ProfileEditionPresenter extends BasePresenter<ProfileEditionView> {

    private UserAuthService authService;

    public ProfileEditionPresenter(ProfileEditionView viewInstance) {
        super(viewInstance);
        authService = UserAuthService.getInstance();
    }

    public void saveProfile() {
        new PeopleService().savePerson(new Person(authService.getUser().getEmail(),
                getView().getUserNameInputText(),
                getView().getUserCityInputText(),
                getView().getUserDateOfBirthInputText(),
                getView().getUserGenderInputText(),
                getView().getProfileCoordinates(),
                getView().getUserNationalityInputText(),
                getView().getUserProfileInputText(),
                authService.getUserProfile().getSkills(),
                authService.getUserProfile().getPreviousExperience(),
                        null, null, null, authService.getUserProfile().getPicture()),
                new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Toast.makeText(getView().getContext(), R.string.profile_saved,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getView().getContext(), R.string.couldnt_save_profile,Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void loadProfile() {
        getView().setUserNameInputText(authService.getUserProfile().getName());
        getView().setUserCityInputText(authService.getUserProfile().getCity());
        getView().setUserDateOfBirthInputText(authService.getUserProfile().getDateOfBirth());
        getView().setUserGenderInputText(authService.getUserProfile().getGender());
        getView().setUserNationalityInputText(authService.getUserProfile().getNationality());
        getView().setUserProfileInputText(authService.getUserProfile().getProfile());
        getView().setNumberOfSkills(authService.getUserProfile().getSkills().size());
        getView().setNumberOfExperiences(authService.getUserProfile().getPreviousExperience().size());
        getView().setUserImage(authService.getUserProfile().getPicture());
        getView().setProfileCoordinates(authService.getUserProfile().getCoordenates());
    }
}
