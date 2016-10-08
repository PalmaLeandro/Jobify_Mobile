package com.example.root.jobify.LoginPage;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.root.jobify.PersonDetailPage.PersonDetailActivity;
import com.example.root.jobify.PersonsListPage.PersonsListActivity;
import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by root on 28/09/16.
 */
public class LoginActivity extends WoloxActivity {


    @Override
    protected int layout() {
        return R.layout.login_page;
    }

    @Override
    protected void init() {
        FloatingActionMenu fabMenu = (FloatingActionMenu) findViewById(R.id.menu_choose);
        fabMenu.setIconAnimated(false);
    }
}
