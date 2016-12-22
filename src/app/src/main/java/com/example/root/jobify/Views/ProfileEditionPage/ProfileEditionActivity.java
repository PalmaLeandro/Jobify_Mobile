package com.example.root.jobify.Views.ProfileEditionPage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxActivity;

import java.io.ByteArrayOutputStream;

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
        replaceFragment(R.id.activity_container,profileEditionFragment);
    }

    private void pickUpImage() {
        verifyStoragePermissions(this);
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            String imgDecodableString;
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.profile_image);
                // Set the Image in ImageView after decoding the String
                Bitmap picture = BitmapFactory
                        .decodeFile(imgDecodableString);
                imgView.setImageBitmap(picture);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                picture.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                UserAuthService.getInstance().getUserProfile().setProfileImage(Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT));

            } else {
                Toast.makeText(this, getString(R.string.no_image_selected_string),
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.couldnt_load_image_string), Toast.LENGTH_LONG)
                    .show();
        }

    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


}
