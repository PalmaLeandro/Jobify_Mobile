package com.example.root.jobify.LoginPage;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cloudrail.si.exceptions.AuthenticationException;
import com.cloudrail.si.interfaces.Profile;
import com.cloudrail.si.services.Facebook;
import com.cloudrail.si.services.GooglePlus;
import com.cloudrail.si.services.LinkedIn;
import com.cloudrail.si.services.Twitter;
import com.example.root.jobify.PersonsListPage.PersonsListActivity;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Social.Communication;
import com.example.root.jobify.Services.Social.SocialAuth;
import com.github.clans.fab.FloatingActionButton;

public class ChooseService extends Fragment {

    private Context mContext;

    private View.OnClickListener mServiceSelectedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.Facebook: {
                    SocialAuth.getInstance().loginWithFacebook(v.getContext());
                    break;
                }
                case R.id.GooglePlus: {
                    SocialAuth.getInstance().loginWithGooglePlus(v.getContext());
                    break;
                }
                default:
                    throw new RuntimeException("Unknown Button ID!!");
            }


        Intent intent = new Intent(v.getContext(), PersonsListActivity.class);
        intent.putExtra(PersonsListActivity.PERSON_USERNAME, "ages");

        v.getContext().startActivity(intent);
        }
    };

    public ChooseService() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choose, container, false);

        FloatingActionButton facebook = (FloatingActionButton) v.findViewById(R.id.Facebook);
        facebook.setOnClickListener(mServiceSelectedListener);
        FloatingActionButton google = (FloatingActionButton) v.findViewById(R.id.GooglePlus);
        google.setOnClickListener(mServiceSelectedListener);

        return v;
    }

}
