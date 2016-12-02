package com.example.root.jobify.Views.ProfileEditionPage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
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
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by root on 09/11/16.
 */
public class ProfileEditionFragment extends WoloxFragment<ProfileEditionPresenter> implements UserAuthListener, View.OnClickListener {

    Context mContext;
    public ProfileEditionView view;
    private double lat;
    private double lng;
    private String coordinates;

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

        Log.d("LocationManager", "QUIERO MI LOCATION MANAGER");

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
//                makeUseOfNewLocation(location);
                if(location != null){
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    setCoordinates(lat,lng);
                }
//                personDetailView.setCoordinates(lat,lng);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("LocationManager", "NO TENGO PERMISOS!!!");
            return;
        }

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);





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
                mPresenter.saveProfile(coordinates);
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



    @Override
    public void onResume() {
        super.onResume();
        populate();
    }

    public void setCoordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;

        coordinates = lat + ":" + lng;
    }
}
