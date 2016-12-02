package com.example.root.jobify.Views.PersonDetailPage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.ExperiencesListPage.ExperienceListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.SkillsListPage.SkillsFragment;

/**
 * Created by root on 23/09/16.
 */
public class PersonDetailActivity extends WoloxActivity{

    PersonDetailPresenter mPresenter;
    PersonDetailView personDetailView;
    ExperienceListFragment experienceListFragment;

    private double lat;
    private double lng;

    public static final String PERSON_USERNAME= ContentListFragment.CONTENT_ID;

    @Override
    protected int layout() {
        return R.layout.main_person_detail_activity;
    }

    @Override
    protected void init() {
        final String fellowId = getIntent().getStringExtra(PERSON_USERNAME);
        personDetailView = new PersonDetailView(findViewById(R.id.main_person_detail_layout));
        SkillsFragment skillsFragment= new SkillsFragment();
        skillsFragment.setPersonId(fellowId);
        skillsFragment.allowDeletion(false);
        replaceFragment(R.id.person_skills_fragment,skillsFragment);
        experienceListFragment = new ExperienceListFragment();
        experienceListFragment.setPersonId(fellowId);
        experienceListFragment.allowDeletion(false);
        replaceFragment(R.id.person_experiences_fragment,experienceListFragment);

        lat = 0;
        lng = 0;

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
//                makeUseOfNewLocation(location);
                if(location != null){
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    personDetailView.setCoordinates(location.toString());
                }
                personDetailView.setCoordinates(lat,lng);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void populate() {
        mPresenter = new PersonDetailPresenter(personDetailView);
        mPresenter.setPersonId(getIntent().getStringExtra(PERSON_USERNAME));
    }

    @Override
    protected void setListeners() {
        personDetailView.recommendProfileButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.recommendFolk();
            }
        });
        personDetailView.unrecommendProfileButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.unrecommendFolk();
            }
        });

    }
}
