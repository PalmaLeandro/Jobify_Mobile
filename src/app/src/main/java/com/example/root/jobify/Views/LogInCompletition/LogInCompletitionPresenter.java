package com.example.root.jobify.Views.LogInCompletition;

import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.BasePresenter;

import retrofit2.Callback;

/**
 * Created by root on 09/11/16.
 */

public class LogInCompletitionPresenter extends BasePresenter<LogInCompletitionView>{

    UserAuthService service ;

    public LogInCompletitionPresenter(LogInCompletitionView viewInstance) {
        super(viewInstance);
        service = UserAuthService.getInstance();
    }

    public void authenticateUser(){
        service.loginWithCredentials(getView().getUserEmailInputValue(),getView().getUserPasswordInputValue());
    }
}