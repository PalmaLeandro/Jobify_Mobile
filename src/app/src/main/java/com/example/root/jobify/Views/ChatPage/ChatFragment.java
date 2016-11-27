package com.example.root.jobify.Views.ChatPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.MessageListPage.MessageListFragment;

/**
 * Created by root on 26/11/16.
 */
public class ChatFragment extends WoloxFragment<ChatPresenter>{

    private static final int MAX_LEGTH_SKILL = 40;
    private FloatingActionButton sendMessageButton;
    private String personId;

    @Override
    protected int layout() {
        return R.layout.chat_page;
    }

    @Override
    protected void setUi(View v) {
        sendMessageButton = (FloatingActionButton) v.findViewById(R.id.send_message);
    }

    @Override
    protected void init() {
        MessageListFragment messageListFragment= new MessageListFragment();
        messageListFragment.setPersonId(personId);
        replaceFragment(R.id.message_list,messageListFragment);
    }

    @Override
    protected void populate() {

    }

    @Override
    protected void setListeners() {
        final Context context = getContext();
        sendMessageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.send_message_modal, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.skill_input);
                userInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LEGTH_SKILL)});
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(R.string.send_string,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        mPresenter.sendMessage(userInput.getText().toString());
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

            }
        });
    }

    @Override
    protected ChatPresenter createPresenter() {
        return new ChatPresenter();
    }


    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
