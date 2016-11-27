package com.example.root.jobify.Views.ChatPage;

import android.widget.Toast;

import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.PersonService;
import com.example.root.jobify.Utilities.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 26/11/16.
 */
public class ChatPresenter extends BasePresenter<ChatFragment>{


    String personId;

    public void setPersonId(String personId) {
        this.personId=personId;
    }

    public void sendMessage(final String message) {
        new PersonService().sendMessage(message,personId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(getView().getContext(), R.string.message_set_string,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getView().getContext(), R.string.message_not_sent_string,Toast.LENGTH_LONG).show();
            }
        });
    }
}
