package com.example.root.jobify.Views;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.User;
import com.example.root.jobify.Services.Auth.UserAuthListener;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.ChatListPage.ChatListFragment;
import com.example.root.jobify.Views.LogInCompletition.LogInCompletitionActivity;
import com.example.root.jobify.Views.MyPeoplePage.MyPeopleFragment;
import com.example.root.jobify.Views.ProfileEditionPage.ProfileEditionFragment;
import com.example.root.jobify.Views.RecomendedFolksPage.RecomendedFolksFragment;


/**
 * Created by root on 15/10/16.
 */

public class MainApplicationActivity extends WoloxActivity implements UserAuthListener {

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
        replaceFragment(R.id.main_content,new RecomendedFolksFragment());
        ab.setTitle("Populares");

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.nav_home: {
                        ab.setTitle(getString(R.string.home_string));
                        replaceFragment(R.id.main_content, new RecomendedFolksFragment());
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
                        replaceFragment(R.id.main_content,new ProfileEditionFragment());
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
        View header=mNavigationView.getHeaderView(0);
        TextView name = (TextView)header.findViewById(R.id.user_name);
        if (user.getName()!=null)
            name.setText(user.getName());
        else
            if (user.getEmail()!=null)
                name.setText(user.getName());
    }

    @Override
    public void onBackPressed() {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
}
