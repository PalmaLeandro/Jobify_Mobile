package com.example.root.jobify.Views;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.ChatListPage.ChatListFragment;
import com.example.root.jobify.Views.LogInCompletition.LogInCompletitionActivity;
import com.example.root.jobify.Views.MyPeoplePage.MyPeopleFragment;
import com.example.root.jobify.Views.ProfileEditionPage.ProfileEditionFragment;
import com.example.root.jobify.Views.ProfileSearchFilterPage.ProfileSearchFragment;
import com.example.root.jobify.Views.RecommendedFolksPage.RecommendedFolksFragment;

import java.io.ByteArrayOutputStream;


/**
 * Created by root on 15/10/16.
 */

public class MainApplicationActivity extends WoloxActivity implements UserAuthListener {

    private static int RESULT_LOAD_IMG = 1;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBar ab;
    UserAuthService userAuthService = UserAuthService.getInstance();

    @Override
    protected int layout() {
        return R.layout.main_application_activity_layout;
    }

    @Override
    protected void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background));
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_titles));

        ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(mNavigationView);

        userAuthService.getInstance().addUserListener(this);
        onUserChanged(userAuthService.getUser());
        replaceFragment(R.id.main_content,new RecommendedFolksFragment());
        ab.setTitle(getString(R.string.recommended_string));
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.nav_home: {
                        ab.setTitle(getString(R.string.recommended_string));
                        replaceFragment(R.id.main_content, new RecommendedFolksFragment());
                        break;
                    }
                    case R.id.nav_search: {
                        ab.setTitle(R.string.search_string);
                        replaceFragment(R.id.main_content, new ProfileSearchFragment());
                        break;
                    }
                    case R.id.nav_mypeople: {
                        ab.setTitle(getString(R.string.contacts_string));
                        replaceFragment(R.id.main_content, new MyPeopleFragment());
                        break;
                    }
                    case R.id.nav_chats: {
                        ab.setTitle(getString(R.string.chats_string));
                        replaceFragment(R.id.main_content,new ChatListFragment());
                        break;
                    }
                    case R.id.nav_profile: {
                        ab.setTitle(getString(R.string.profile_string));
                        ProfileEditionFragment profileEditionFragment= new ProfileEditionFragment();
                        profileEditionFragment.setImageButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pickUpImage();
                            }
                        });
                        replaceFragment(R.id.main_content,profileEditionFragment);
                        break;
                    }
                    case R.id.nav_exit: {
                        userAuthService.logout();
                        Intent intent = new Intent(getApplicationContext(), LogInCompletitionActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent);
                        break;
                    }
                    default:
                        throw new RuntimeException("Unknown Menu ID!!");
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        mNavigationView.getMenu().getItem(0).setChecked(true);
    }


    @Override
    public void onUserChanged(User user) {
        if(user!=null){
            View header=mNavigationView.getHeaderView(0);
            TextView name = (TextView)header.findViewById(R.id.user_name);
            if (user.getName()!=null)
                name.setText(user.getName());
            else
            if (user.getEmail()!=null)
                name.setText(user.getName());
        }
    }

    @Override
    public void onBackPressed() {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    private void pickUpImage() {
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
                userAuthService.getUserProfile().setProfileImage(Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT));

            } else {
                Toast.makeText(this, getString(R.string.no_image_selected_string),
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.couldnt_load_image_string), Toast.LENGTH_LONG)
                    .show();
        }

    }
}
