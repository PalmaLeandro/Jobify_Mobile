package com.example.root.jobify.Views.LogInCompletition;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.jobify.R;

/**
 * Created by root on 09/11/16.
 */

public class LogInCompletitionView {
    final TextView godModeButton;

    public Context getContext() {
        return context;
    }

    private Context context;

    public LogInCompletitionView(View view) {
        context=view.getContext();
        this.userEmailInput = (EditText) view.findViewById(R.id.user_email_input);
        this.userPasswordInput = (EditText) view.findViewById(R.id.user_password_input);
        this.logInButton = (Button) view.findViewById(R.id.credentials_button_login);
        facebookButton = (Button) view.findViewById(R.id.facebook_button);
        signUpButton = (TextView) view.findViewById(R.id.choose_signup_button);
        godModeButton = (TextView) view.findViewById(R.id.app_title);
    }


    EditText userEmailInput;


    public String getUserPasswordInputValue() {
        return userPasswordInput.getText().toString();
    }

    public String getUserEmailInputValue() {
        return userEmailInput.getText().toString();
    }

    EditText userPasswordInput;

    public Button getLogInButton() {
        return logInButton;
    }

    Button logInButton;
    Button facebookButton;
    TextView signUpButton;
    private ProgressDialog progressDialog;

    public void showProgressDialog() {
        progressDialog =  ProgressDialog.show(context, "",
                context.getString(R.string.loging_in_string), true);
    }

    public void hideProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
