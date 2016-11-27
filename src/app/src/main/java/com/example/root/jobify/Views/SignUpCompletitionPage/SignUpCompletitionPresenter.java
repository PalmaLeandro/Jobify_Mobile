package com.example.root.jobify.Views.SignUpCompletitionPage;

import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.BasePresenter;

import retrofit2.Callback;

/**
 * Created by root on 09/11/16.
 */

public class SignUpCompletitionPresenter extends BasePresenter<SignUpCompletitionView> {


    public SignUpCompletitionPresenter(SignUpCompletitionView viewInstance) {
        super(viewInstance);
    }

    public void registerUser() {
        UserAuthService.getInstance().signUpWithCredentials(getView().getUserNameInputValue(),getView().getUserEmailInputValue(),getView().getUserPasswordInputValue());
    }
}
