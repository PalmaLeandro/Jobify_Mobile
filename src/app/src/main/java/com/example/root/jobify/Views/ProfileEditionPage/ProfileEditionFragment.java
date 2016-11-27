package com.example.root.jobify.Views.ProfileEditionPage;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.ExperiencesEditionPage.ExperiencesEditionActivity;
import com.example.root.jobify.Views.MainApplicationActivity;
import com.example.root.jobify.Views.SkillsEditionPage.SkillsEditionActivity;

/**
 * Created by root on 09/11/16.
 */
public class ProfileEditionFragment extends WoloxFragment<ProfileEditionPresenter> implements UserAuthListener, View.OnClickListener {

    Context mContext;
    ProfileEditionView view;

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
        view.pickImageButton.setOnClickListener(this);
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
            case R.id.pick_image_button: {
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
