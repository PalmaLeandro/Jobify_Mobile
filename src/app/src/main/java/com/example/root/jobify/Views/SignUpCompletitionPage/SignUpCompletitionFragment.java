package com.example.root.jobify.Views.SignUpCompletitionPage;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.FacebookSignUpPage.FacebookSignUpActivity;
import com.example.root.jobify.Views.MainApplicationActivity;

/**
 * Created by root on 09/11/16.
 */
public class SignUpCompletitionFragment extends WoloxFragment<SignUpCompletitionPresenter> implements UserAuthListener, View.OnClickListener {

    Context mContext;
    SignUpCompletitionView view;
    private User user;

    @Override
    protected int layout() {
        return R.layout.signup_completition_page;
    }

    @Override
    protected void setUi(View v) {
        mContext = v.getContext();
        view=new SignUpCompletitionView(v);
    }

    @Override
    protected void init() {
        UserAuthService.getInstance().addUserListener(this);
    }

    @Override
    protected void populate() {
        mPresenter=createPresenter();
    }

    @Override
    protected void setListeners() {
        view.signUpButton.setOnClickListener(this);
        view.facebookButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.credentials_button_signup: {
                mPresenter.registerUser();
                break;
            }
            case R.id.facebook_button: {
                v.getContext().startActivity(new Intent(v.getContext(), FacebookSignUpActivity.class));
                break;
            }
            default:
                throw new RuntimeException("Unknown Button ID!!");
        }
    }

    @Override
    protected SignUpCompletitionPresenter createPresenter() {
        return new SignUpCompletitionPresenter(view);
    }

    @Override
    public void onUserChanged(User user) {
        view.hideProgressDialog();
        if (this.user==null && user!=null){
            this.user=user;
            mContext.startActivity(new Intent(mContext, MainApplicationActivity.class));
        } else {
            this.user = user;
            //Toast.makeText(getActivity(), R.string.couldnt_login_string,Toast.LENGTH_LONG).show();
        }
    }
}
