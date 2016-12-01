package com.example.root.jobify.Views.ChatListPage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.PersonService;
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
                new PersonService().getMyChats(new Callback<ArrayList<Person>>() {
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

                                @Override
                                public String getBase64Image() {
                                    return content.getBase64Image();
                                }
                            });
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                        ArrayList<Experience> previous_exp = new ArrayList<Experience>();
                        previous_exp.add(new Experience("1", "google","barrer pisos","asistente de limpieza","2010 - Actualidad"));
                        previous_exp.add(new Experience("1", "microsoft","barrer pasillos","asistente de limpieza","2009 - 2010"));
                        ArrayList< String > skills = new ArrayList<>();
                        skills.add("lavar platos");
                        skills.add("barrer");
                        skills.add("trapear");
                        skills.add("lavar vidrios");
                        ArrayList<Person> personArrayList= new ArrayList<Person>();
                        personArrayList.add(new Person("leopalma","san antonio de areco","25/10/1993","leandropalma0@gmail.com","male","Leandro Palma","Arg",previous_exp,"soy capo de la limpieza y tambien la muevo en la cocina",skills,null));
                        personArrayList.add(new Person("leopalma","san antonio de areco","25/10/1993","leandropalma0@gmail.com","male","Leandro Palma","Arg",previous_exp,"soy capo de la limpieza y tambien la muevo en la cocina",skills,null));
                        personArrayList.add(new Person("leopalma","san antonio de areco","25/10/1993","leandropalma0@gmail.com","male","Leandro Palma","Arg",previous_exp,"soy capo de la limpieza y tambien la muevo en la cocina",skills,null));

                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (final Content content : personArrayList)
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

                                @Override
                                public String getBase64Image() {
                                    return content.getBase64Image();
                                }
                            });
                        callback.onResponse(null, Response.success(contents));

                    }
                });
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
                                new PersonService().deleteChat(content.getId(), new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        Toast.makeText(context, R.string.chat_deleted_text_string,Toast.LENGTH_LONG).show();
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
