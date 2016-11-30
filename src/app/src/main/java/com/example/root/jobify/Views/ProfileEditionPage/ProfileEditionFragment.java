package com.example.root.jobify.Views.ProfileEditionPage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.ExperiencesEditionPage.ExperiencesEditionActivity;
import com.example.root.jobify.Views.MainApplicationActivity;
import com.example.root.jobify.Views.SkillsEditionPage.SkillsEditionActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by root on 09/11/16.
 */
public class ProfileEditionFragment extends WoloxFragment<ProfileEditionPresenter> implements UserAuthListener, View.OnClickListener {

    Context mContext;
    public ProfileEditionView view;

    //private Object mGoogleApiClient;

    public void setImageButtonClickListener(View.OnClickListener imageButtonClickListener) {
        this.imageButtonClickListener = imageButtonClickListener;
    }

    private View.OnClickListener imageButtonClickListener;

    @Override
    protected int layout() {
        return R.layout.profile_edition_page;
    }

    @Override
    protected void setUi(View v) {
        mContext = v.getContext();
        view=new ProfileEditionView(v);
    }

    @Override
    protected void init() {
        /*
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
                */
    }

    @Override
    protected void populate() {
        mPresenter=createPresenter();
        mPresenter.loadProfile();
    }

    @Override
    protected void setListeners() {
        view.skillsButton.setOnClickListener(this);
        view.experiencesButton.setOnClickListener(this);
        view.saveProfileButton.setOnClickListener(this);
        view.pickImageButton.setOnClickListener(imageButtonClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skills_button_access: {
                v.getContext().startActivity(new Intent(v.getContext(), SkillsEditionActivity.class));
                break;
            }
            case R.id.experiences_button_access: {
                v.getContext().startActivity(new Intent(v.getContext(), ExperiencesEditionActivity.class));
                break;
            }
            case R.id.save_profile: {
                mPresenter.saveProfile();
                break;
            }
            default:
                throw new RuntimeException("Unknown Button ID!!");
        }
    }

    @Override
    protected ProfileEditionPresenter createPresenter() {
        return new ProfileEditionPresenter(view);
    }

    @Override
    public void onUserChanged(User user) {
        view.hideProgressDialog();
        if (user!=null){
            mContext.startActivity(new Intent(mContext, MainApplicationActivity.class));
        }
    }

}
