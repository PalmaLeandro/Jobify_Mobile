package com.example.root.jobify.Views.LogInCompletition;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.jobify.Globals;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.FacebookLogInPage.FacebookLogInActivity;
import com.example.root.jobify.Views.MainApplicationActivity;
import com.example.root.jobify.Views.SignUpCompletitionPage.SignUpCompletitionActivity;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by root on 09/11/16.
 */
public class LogInCompletitionFragment extends WoloxFragment<LogInCompletitionPresenter> implements UserAuthListener, View.OnClickListener {

    LogInCompletitionView view;
    Context mContext;
    private int bossHealth=13;
    User user;

    @Override
    public void onUserChanged(User user) {
        view.hideProgressDialog();
        if (this.user==null && user!=null){
            this.user=user;
            mContext.startActivity(new Intent(mContext, MainApplicationActivity.class));
        } else {
            this.user = user;
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
        view.godModeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String firebaseToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("MAIN ACTIVITY", "Refreshed firebase token: " + firebaseToken);
        switch (v.getId()) {
            case R.id.credentials_button_login: {
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
            case R.id.app_title: {
                attackBoss();
                if (bossHealth<=0){
                    LayoutInflater li = LayoutInflater.from(getContext());
                    final View promptsView = li.inflate(R.layout.set_server_address_modal, null);
                    final EditText serverAddressInput = (EditText) promptsView.findViewById(R.id.server_address_input);
                    serverAddressInput.setText(Globals.getServerAddress());
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            getContext());

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton(R.string.set_button_string,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            Globals.setServerAddress(serverAddressInput.getText().toString());
                                        }
                                    })
                            .setNegativeButton(R.string.cancel_button_string,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
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

    void attackBoss(){
        bossHealth--;
        switch (bossHealth){
            case 1:{
                Toast.makeText(getContext(),"Well, this is boring, so enjoy yourself",Toast.LENGTH_SHORT).show();
                break;
            }
            case 2:{
                Toast.makeText(getContext(),"There, you have no more friends",Toast.LENGTH_SHORT).show();
                break;
            }
            case 3:{
                Toast.makeText(getContext(),"I have it, hit me again and i will post something awful on Facebook as you. No kidding!",Toast.LENGTH_SHORT).show();
                break;
            }
            case 4:{
                Toast.makeText(getContext(),"Data is already deleted. What else?",Toast.LENGTH_SHORT).show();
                break;
            }
            case 5:{
                Toast.makeText(getContext(),"Hit me again and i will erase all your data",Toast.LENGTH_SHORT).show();
                break;
            }
            case 6:{
                Toast.makeText(getContext(),"Okay, you asked for this",Toast.LENGTH_SHORT).show();
                break;
            }
            case 7:{
                Toast.makeText(getContext(),"This is your last chance",Toast.LENGTH_SHORT).show();
                break;
            }
            case 8:{
                Toast.makeText(getContext(),"How you dare!!!",Toast.LENGTH_SHORT).show();
                break;
            }
            case 9:{
                Toast.makeText(getContext(),"You will pay for this!",Toast.LENGTH_SHORT).show();
                break;
            }
            case 10:{
                Toast.makeText(getContext(),"Oh no, you hit me!",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
