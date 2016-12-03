package com.example.root.jobify.Views.ChatListPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.root.jobify.Models.JobPosition;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.ChatPage.ChatActivity;
import com.example.root.jobify.Views.GenericContentListPage.Content;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 06/09/16.
 */
public class ChatListFragment extends ContentListFragment {

    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PeopleService().getMyChats(new Callback<ArrayList<Person>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (final Content content : response.body())
                            contents.add(new Content() {
                                @Override
                                public String getId() {
                                    return content.getId();
                                }

                                @Override
                                public String getTitle() {
                                    return content.getTitle();
                                }

                                @Override
                                public String getSubTitle() {
                                    return null;
                                }

                                public String getPicture() {
                                    return content.getPicture();
                                }
                            });
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                       callback.onFailure(null,t);
                    }
                });
            }
        };
    }


    @Override
    public void setListener(final Content content, View contentView) {
        super.setListener(content,contentView);
        contentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Context context = v.getContext();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set dialog message
                alertDialogBuilder
                        .setTitle(getString(R.string.delete_chat_text_string)+" "+content.getTitle())
                        .setItems(new String[]{context.getString(R.string.remove_dialog_option_string)}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new PeopleService().deleteChat(content.getId(), new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        Toast.makeText(context, R.string.chat_deleted_text_string,Toast.LENGTH_LONG).show();
                                        populate();
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Toast.makeText(context, R.string.chat_deletion_error,Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        })
                        .setCancelable(true);

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


                return true;
            }
        });
    }

    @Override
    public Class<? extends WoloxActivity> getContentDetailActivity() {
        return ChatActivity.class;
    }

}
