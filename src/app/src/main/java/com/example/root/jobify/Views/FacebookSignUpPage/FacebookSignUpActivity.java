package com.example.root.jobify.Views.FacebookSignUpPage;

import android.app.ProgressDialog;
import android.content.Intent;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.MainApplicationActivity;
import com.example.root.jobify.Views.SignUpCompletitionPage.SignUpCompletitionActivity;

/**
 * Created by root on 10/11/16.
 */

public class FacebookSignUpActivity extends WoloxActivity implements UserAuthListener{
    private ProgressDialog progressDialog;

    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        showProgressDialog();
        UserAuthService.getInstance().addUserListener(this);
        UserAuthService.getInstance().signUpWithFacebook(this);
    }

    private void showProgressDialog() {
        progressDialog =  ProgressDialog.show(this, "",
                getString(R.string.loging_in_string), true);
    }

    @Override
    public void onUserChanged(User user) {
        progressDialog.dismiss();
        if(user!=null){
            this.startActivity(new Intent(this, MainApplicationActivity.class));
        } else{
            this.startActivity(new Intent(this, SignUpCompletitionActivity.class));
        }
    }
}
