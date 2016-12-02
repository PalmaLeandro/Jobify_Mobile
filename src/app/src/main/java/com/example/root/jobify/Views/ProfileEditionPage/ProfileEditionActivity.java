package com.example.root.jobify.Views.ProfileEditionPage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

/**
 * Created by root on 09/11/16.
 */

public class ProfileEditionActivity extends WoloxActivity {
    private double lat;
    private double lng;

    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {

        final ProfileEditionFragment pf = new ProfileEditionFragment();







//        pf.setCoordinates(lat,lng);




        replaceFragment(R.id.activity_container, pf);
    }



}
