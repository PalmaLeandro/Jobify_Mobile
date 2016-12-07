package com.example.root.jobify.Views.MessageListPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.jobify.Models.Message;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Views.GenericContentListPage.Content;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListProvider;
import com.example.root.jobify.Views.GenericContentListPage.SimpleDividerItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 06/09/16.
 */
public class MessageListFragment extends ContentListFragment{

    private String personId;
    private TextView subcontentTextView;

    @Override
    protected int layout() {
        return R.layout.subcontent_list_fragment;
    }

    @Override
    protected void setUi(View v) {
        super.setUi(v);
        subcontentTextView=(TextView) v.findViewById(R.id.subcontent_title);
        subcontentTextView.setText(R.string.message_list_title_string);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mRecyclerView.getContext()));
    }

    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PeopleService().getChatMessages(personId, new Callback<ArrayList<Message>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (Content content : response.body())
                            contents.add(content);
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                        callback.onFailure(null,t);
                    }
                });
            }
        };
    }

    @Override
    public void setListener(final Content content, View contentView) {
        if (content.getTitle().equals(UserAuthService.getInstance().getUserProfile().getName())||content.getTitle().equals(UserAuthService.getInstance().getUserProfile().getEmail())){
            contentView.findViewById(R.id.content_title).setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            contentView.findViewById(R.id.content_subtitle).setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            contentView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Context context = v.getContext();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set dialog message
                    alertDialogBuilder
                            .setTitle(getString(R.string.delete_message_modal_title_string)+content.getSubTitle())
                            .setItems(new String[]{context.getString(R.string.remove_dialog_option_string)}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new PeopleService().deleteMessage(content.getId(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            Toast.makeText(context, R.string.message_deleted_text_string,Toast.LENGTH_LONG).show();
                                            populate();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Toast.makeText(context, R.string.message_deletion_error,Toast.LENGTH_LONG).show();;
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
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
