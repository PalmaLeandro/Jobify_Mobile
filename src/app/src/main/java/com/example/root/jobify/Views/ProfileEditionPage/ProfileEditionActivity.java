package com.example.root.jobify.Views.ProfileEditionPage;

import android.content.Intent;
import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;

/**
 * Created by root on 09/11/16.
 */

public class ProfileEditionActivity extends WoloxActivity {

    private static int RESULT_LOAD_IMG = 1;

    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        ProfileEditionFragment profileEditionFragment = new ProfileEditionFragment();
        profileEditionFragment.setImageButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUpImage();
            }
        });
        replaceFragment(R.id.activity_container,new ProfileEditionFragment());
    }

    private void pickUpImage() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }



}
