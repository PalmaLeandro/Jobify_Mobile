package com.example.root.jobify.Views.SignUpCompletitionPage;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.root.jobify.R;

/**
 * Created by root on 09/11/16.
 */

public class SignUpCompletitionView {
    private Context context;

    public SignUpCompletitionView(View view) {
        context=view.getContext();
        this.userNameInput = (EditText) view.findViewById(R.id.user_name_input);
        this.userEmailInput = (EditText) view.findViewById(R.id.user_email_input);
        this.userPasswordInput = (EditText) view.findViewById(R.id.user_password_input);
        this.signUpButton = (Button) view.findViewById(R.id.credentials_button_signup);
        facebookButton = (Button) view.findViewById(R.id.facebook_button);
    }

    EditText userNameInput;

    EditText userEmailInput;

    public String getUserNameInputValue() {
        return userNameInput.getText().toString();
    }

    public String getUserPasswordInputValue() {
        return userPasswordInput.getText().toString();
    }

    public String getUserEmailInputValue() {
        return userEmailInput.getText().toString();
    }

    EditText userPasswordInput;

    public Button getSignUpButton() {
        return signUpButton;
    }

    Button signUpButton;
    Button facebookButton;
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

    public Context getContext() {
        return context;
    }
}
