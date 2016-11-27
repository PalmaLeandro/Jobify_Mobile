package com.example.root.jobify.Views.PersonDetailPage;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.People.SinglePersonProvider;
import com.example.root.jobify.Utilities.BasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 07/09/16.
 */
public class PersonDetailPresenter extends BasePresenter<PersonDetailView> {

    Person mPerson;
    String personId;
    private ProgressDialog progressDialog;

    public PersonDetailPresenter(PersonDetailView viewInstance) {
        super(viewInstance);
    }

    public void loadPerson() {
        PersonDetailView personDetailView = getView();
        personDetailView.setPersonImageURL(mPerson.getBase64Image());
        personDetailView.setPersonName(mPerson.getName());
        personDetailView.setPersonCity(mPerson.getCity());
        personDetailView.setPersonDob(mPerson.getDateOfBirth());
        personDetailView.setPersonEmail(mPerson.getEmail());
        personDetailView.setPersonGender(mPerson.getGender());
        personDetailView.setPersonNationality(mPerson.getNationality());
        personDetailView.setPersonProfile(mPerson.getProfile());
        updateRecomendationButons();
        updatePersonPrimaryAction();
    }

    private void updateRecomendationButons(){
        if (mPerson.isAlreadyRecomendedByCurrentUser()){
            getView().hideRecommendProfileButtton();
            getView().showUnrecommendProfileButtton();
        } else {
            getView().hideUnrecommendProfileButtton();
            getView().showRecommendProfileButtton();
        }
    }


    public void updatePersonPrimaryAction(){
        PersonDetailView personDetailView = getView();
        if (mPerson.userIsAlreadyAddedByCurrentProfile()){
            personDetailView.setPersonInscriptionActionIcon(R.drawable.ic_not_interested_white_24dp);
            personDetailView.mPersonActionFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    mPerson.changeProfileAddition();
                    new SinglePersonProvider().removePerson(mPerson.getUsername(), new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Snackbar.make(view, R.string.person_removed_string, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.undo_string, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new SinglePersonProvider().addPerson(mPerson.getUsername(), new Callback() {
                                                @Override
                                                public void onResponse(Call call, Response response) {
                                                    mPerson.changeProfileAddition();
                                                    loadPerson();
                                                }

                                                @Override
                                                public void onFailure(Call call, Throwable t) {
                                                    Snackbar.make(view, R.string.cant_add_person_string, Snackbar.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    }).show();
                            loadPerson();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Snackbar.make(view, R.string.cant_remove_person_string, Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } else {
            personDetailView.setPersonInscriptionActionIcon(R.drawable.ic_add_plus_24dp);
            personDetailView.mPersonActionFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    mPerson.changeProfileAddition();
                    new SinglePersonProvider().addPerson(mPerson.getUsername(), new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Snackbar.make(view, R.string.person_added_string, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.undo_string, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new SinglePersonProvider().removePerson(mPerson.getUsername(), new Callback() {
                                                @Override
                                                public void onResponse(Call call, Response response) {
                                                    mPerson.changeProfileAddition();
                                                    loadPerson();
                                                }

                                                @Override
                                                public void onFailure(Call call, Throwable t) {
                                                    Snackbar.make(view, R.string.cant_add_person_string, Snackbar.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    }).show();
                            loadPerson();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Snackbar.make(view, R.string.cant_remove_person_string, Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }

    }

    public void setPersonId(String personId) {
        this.personId=personId;
        showProgressDialog();
        new SinglePersonProvider().getPerson(personId,new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                //mPerson = response.body();
                loadPerson();
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("GET_PERSON",getView().getContext().getString(R.string.cant_fetch_person_string),t);
                hideProgressDialog();

                ArrayList< Experience > previous_exp = new ArrayList<Experience>();
                previous_exp.add(new Experience("1", "google","barrer pisos","asistente de limpieza","2010 - Actualidad"));
                previous_exp.add(new Experience("1", "microsoft","barrer pasillos","asistente de limpieza","2009 - 2010"));
                ArrayList< String > skills = new ArrayList<>();
                skills.add("lavar platos");
                skills.add("barrer");
                skills.add("trapear");
                skills.add("lavar vidrios");
                mPerson = new Person("leopalma","san antonio de areco","25/10/1993","leandropalma0@gmail.com","male","Leandro Palma","Arg",previous_exp,"soy capo de la limpieza y tambien la muevo en la cocina",skills,null);
                loadPerson();
            }
        });
    }

    private void showProgressDialog(){
        progressDialog =  ProgressDialog.show(getView().getContext(), "",
                "Buscando datos. Por favor espere...", true);
    }

    private void hideProgressDialog(){
        if (progressDialog!=null)
            progressDialog.dismiss();
    }

    void recommendFolk(){
        new SinglePersonProvider().recommendFolk(personId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                updateRecomendationButons();
                Snackbar.make(getView().recommendProfileButtton, R.string.person_added_string, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo_string, new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                new SinglePersonProvider().unrecommendFolk(mPerson.getUsername(), new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        updateRecomendationButons();
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Snackbar.make(v, R.string.cant_add_person_string, Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }


    void unrecommendFolk(){
        new SinglePersonProvider().unrecommendFolk(personId, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                updateRecomendationButons();
                Snackbar.make(getView().recommendProfileButtton, R.string.person_added_string, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo_string, new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                new SinglePersonProvider().recommendFolk(mPerson.getUsername(), new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        updateRecomendationButons();
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Snackbar.make(v, R.string.cant_add_person_string, Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
