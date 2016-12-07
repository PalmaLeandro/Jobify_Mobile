package com.example.root.jobify.Views.ProfileEditionPage;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
public class ProfileEditionFragment extends WoloxFragment<ProfileEditionPresenter> implements View.OnClickListener {

    Context mContext;
    public ProfileEditionView view;
    private double lat;
    private double lng;

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
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
//                makeUseOfNewLocation(location);
                if(location != null){
                    setCoordinates(location.getLatitude(),location.getLongitude());
                }
//                personDetailView.setCoordinates(lat,lng);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, locationListener);


    }

    private void setCoordinates(double lat, double lng) {
        view.profileCoordinates= Double.valueOf(lat).toString()+":"+Double.valueOf(lng).toString();
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
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getContext());

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getContext());

                // set dialog message
                alertDialogBuilder
                        .setMessage(R.string.save_profile_message)
                        .setCancelable(false)
                        .setPositiveButton(R.string.send_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mPresenter.saveProfile();
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
    public void onResume() {
        super.onResume();
        populate();
    }

}
