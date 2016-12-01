package com.example.root.jobify.Views.LogInCompletition;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.FacebookLogInPage.FacebookLogInActivity;
import com.example.root.jobify.Views.MainApplicationActivity;
import com.example.root.jobify.Views.SignUpCompletitionPage.SignUpCompletitionActivity;

/**
 * Created by root on 09/11/16.
 */
public class LogInCompletitionFragment extends WoloxFragment<LogInCompletitionPresenter> implements UserAuthListener, View.OnClickListener {

    LogInCompletitionView view;
    Context mContext;

    @Override
    public void onUserChanged(User user) {
        view.hideProgressDialog();
        if (user!=null){
            mContext.startActivity(new Intent(mContext, MainApplicationActivity.class));
        }
    }


    @Override
    protected int layout() {
        return R.layout.login_completition_page;
    }

    @Override
    protected void setUi(View v) {
        view = new LogInCompletitionView(v);
        mContext=v.getContext();
        mPresenter= createPresenter();
    }

    @Override
    protected void init() {
        UserAuthService.getInstance().addUserListener(this);
    }

    @Override
    protected void populate() {

    }

    @Override
    protected void setListeners() {
        view.logInButton.setOnClickListener(this);
        view.facebookButton.setOnClickListener(this);
        view.signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.credentials_button_login: {
                view.showProgressDialog();
                mPresenter.authenticateUser();
                break;
            }
            case R.id.facebook_button: {
                v.getContext().startActivity(new Intent(v.getContext(), FacebookLogInActivity.class));
                break;
            }
            case R.id.choose_signup_button: {
                v.getContext().startActivity(new Intent(v.getContext(), SignUpCompletitionActivity.class));
                break;
            }
            default:
                throw new RuntimeException("Unknown Button ID!!");
        }
    }

    @Override
    protected LogInCompletitionPresenter createPresenter() {
        return new LogInCompletitionPresenter(view);
    }
}
