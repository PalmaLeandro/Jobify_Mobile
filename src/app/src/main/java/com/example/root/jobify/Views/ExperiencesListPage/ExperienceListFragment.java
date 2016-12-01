package com.example.root.jobify.Views.ExperiencesListPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.R;
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
public class ExperienceListFragment extends ContentListFragment{

    private String personId;
    private TextView subcontentTextView;

    public void allowDeletion(boolean allowDeletion) {
        this.allowDeletion = allowDeletion;
    }

    private boolean allowDeletion;

    @Override
    protected int layout() {
        return R.layout.subcontent_list_fragment;
    }

    @Override
    protected void setUi(View v) {
        super.setUi(v);
        subcontentTextView=(TextView) v.findViewById(R.id.subcontent_title);
        subcontentTextView.setText(v.getContext().getResources().getText(R.string.experience));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mRecyclerView.getContext()));
    }

    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PeopleService().getPersonExperiences(personId, new Callback<ArrayList<Experience>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Experience>> call, Response<ArrayList<Experience>> response) {
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (Content content : response.body())
                            contents.add(content);
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Experience>> call, Throwable t) {
                        callback.onFailure(null,t);
                    }
                });
            }
        };
    }

    @Override
    public void setListener(final Content content, View contentView) {
        if (allowDeletion){
            contentView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Context context = v.getContext();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set dialog message
                    alertDialogBuilder
                            .setTitle("Remove "+content.getTitle())
                            .setItems(new String[]{context.getString(R.string.remove_dialog_option_string)}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new PeopleService().removeProfileExperience(content.getId(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            Toast.makeText(context,getString(R.string.removed_skill_message),Toast.LENGTH_LONG);
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Toast.makeText(context, R.string.couldnt_remove_skill_message,Toast.LENGTH_LONG);
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
