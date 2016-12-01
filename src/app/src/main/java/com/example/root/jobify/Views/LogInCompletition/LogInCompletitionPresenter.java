package com.example.root.jobify.Views.LogInCompletition;

import android.widget.Toast;

import com.cloudrail.si.interfaces.Email;
import com.example.root.jobify.Globals;
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
        if (isValidLogin()){
            getView().showProgressDialog();
            service.loginWithCredentials(getView().getUserEmailInputValue(),getView().getUserPasswordInputValue());
        }
    }

    private boolean isValidLogin(){
        return Globals.areValidCredentials(getView().getUserEmailInputValue(),getView().getUserPasswordInputValue(),getView().getContext());
    }
}