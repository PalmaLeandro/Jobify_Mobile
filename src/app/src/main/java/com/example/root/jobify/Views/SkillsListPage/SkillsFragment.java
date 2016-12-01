package com.example.root.jobify.Views.SkillsListPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.jobify.Models.JobPosition;
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
public class SkillsFragment extends ContentListFragment {

    private String personId;

    @Override
    protected int layout() {
        return R.layout.subcontent_list_fragment;
    }

    @Override
    protected void setUi(View v) {
        super.setUi(v);
        TextView subcontentTextView=(TextView) v.findViewById(R.id.subcontent_title);
        subcontentTextView.setText(v.getContext().getResources().getText(R.string.skills_title_string));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(mRecyclerView.getContext()));
    }

    private Boolean allowDeletion;

    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PeopleService().getPersonSkills(new Callback<ArrayList<String>>() {
                    @Override
                    public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (String content : response.body())
                            contents.add(createContentFromSkill(content));
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                        callback.onFailure(null,t);
                    }
                });
            }
        };
    }

    private Content createContentFromSkill(final String skill){
        return new Content() {
            @Override
            public String getId() {
                return skill;
            }

            @Override
            public String getTitle() {
                return skill;
            }

            @Override
            public String getSubTitle() {
                return null;
            }

            public String getPicture() {
                return null;
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        populate();
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
                            .setTitle(getString(R.string.remove_modal_title_start)+content.getTitle())
                            .setItems(new String[]{context.getString(R.string.remove_dialog_option_string)}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new PeopleService().removeProfileSkill(content.getId(), new Callback() {
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

    public void allowDeletion(Boolean allowDeletion) {
        this.allowDeletion = allowDeletion;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }

}
