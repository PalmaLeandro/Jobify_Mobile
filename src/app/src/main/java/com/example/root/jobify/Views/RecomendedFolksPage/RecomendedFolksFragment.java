package com.example.root.jobify.Views.RecomendedFolksPage;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.People.PersonService;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Views.GenericContentListPage.Content;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListProvider;
import com.example.root.jobify.Views.PersonDetailPage.PersonDetailActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 20/11/16.
 */
public class RecomendedFolksFragment extends ContentListFragment{
    @Override
    protected ContentListProvider createService() {
        return new ContentListProvider() {
            @Override
            public void getContents(final Callback<ArrayList<Content>> callback) {
                new PersonService().getRecomendedFolks(new Callback<ArrayList<Person>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (Content content : response.body())
                            contents.add(content);
                        callback.onResponse(null, Response.success(contents));
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                        ArrayList< Experience > previous_exp = new ArrayList<Experience>();
                        previous_exp.add(new Experience("1", "google","barrer pisos","asistente de limpieza","2010 - Actualidad"));
                        previous_exp.add(new Experience("1", "microsoft","barrer pasillos","asistente de limpieza","2009 - 2010"));
                        ArrayList< String > skills = new ArrayList<>();
                        skills.add("lavar platos");
                        skills.add("barrer");
                        skills.add("trapear");
                        skills.add("lavar vidrios");
                        ArrayList<Person> personArrayList= new ArrayList<Person>();
                        personArrayList.add(new Person("leopalma","san antonio de areco","25/10/1993","leandropalma0@gmail.com","male","Leandro Palma","Arg",previous_exp,"soy capo de la limpieza y tambien la muevo en la cocina",skills,null));
                        ArrayList<Content> contents = new ArrayList<Content>();
                        for (Content content : personArrayList)
                            contents.add(content);
                        callback.onResponse(null, Response.success(contents));

                    }
                });
            }
        };
    }

    @Override
    public Class<? extends WoloxActivity> getContentDetailActivity() {
        return PersonDetailActivity.class;
    }
}
