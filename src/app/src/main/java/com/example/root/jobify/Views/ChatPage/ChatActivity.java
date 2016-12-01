package com.example.root.jobify.Views.ChatPage;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;

/**
 * Created by root on 26/11/16.
 */

public class ChatActivity extends WoloxActivity {
    @Override
    protected int layout() {
        return R.layout.activity_container;
    }

    @Override
    protected void init() {
        ChatFragment chatFragment = new ChatFragment();
        replaceFragment(R.id.activity_container,chatFragment);
        chatFragment.setPersonId(getIntent().getStringExtra(ContentListFragment.CONTENT_ID));
    }
}
