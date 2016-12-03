package com.example.root.jobify.Views.PersonDetailPage;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.root.jobify.Deserializers.ServerResponse;
import com.example.root.jobify.Models.Message;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Services.People.PeopleService;
import com.example.root.jobify.Services.People.SinglePersonProvider;
import com.example.root.jobify.Utilities.BasePresenter;

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
        personDetailView.setPersonImageURL(mPerson.getPicture());
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
        if (new PeopleService().isAlreadyRecomendedByCurrentUser(mPerson)){
            getView().hideRecommendProfileButtton();
            getView().showUnrecommendProfileButtton();
        } else {
            getView().hideUnrecommendProfileButtton();
            getView().showRecommendProfileButtton();
        }
    }


    public void updatePersonPrimaryAction(){
        PersonDetailView personDetailView = getView();
        if (new PeopleService().userIsAlreadyAddedByCurrentUser(mPerson)){
            personDetailView.setPersonInscriptionActionIcon(R.drawable.ic_not_interested_white_24dp);
            personDetailView.mPersonActionFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    new SinglePersonProvider().removePerson(mPerson, new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Snackbar.make(view, R.string.person_removed_string, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.undo_string, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new SinglePersonProvider().addPerson(mPerson, new Callback() {
                                                @Override
                                                public void onResponse(Call call, Response response) {
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
                    new SinglePersonProvider().addPerson(mPerson, new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Snackbar.make(view, R.string.person_added_string, Snackbar.LENGTH_LONG)
                                    .setAction(R.string.undo_string, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new SinglePersonProvider().removePerson(mPerson, new Callback() {
                                                @Override
                                                public void onResponse(Call call, Response response) {
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
        new SinglePersonProvider().getPerson(personId,new Callback<ServerResponse<Person>>() {
            @Override
            public void onResponse(Call<ServerResponse<Person>> call, Response<ServerResponse<Person>> response) {
                if(response.body()!=null && response.body().data!=null){
                    mPerson = response.body().data;
                    loadPerson();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<ServerResponse<Person>> call, Throwable t) {
                Log.e("GET_PERSON", getView().getContext().getString(R.string.cant_fetch_person_string), t);
                Toast.makeText(getView().getContext(), getView().getContext().getString(R.string.cant_fetch_person_string), Toast.LENGTH_LONG).show();
                hideProgressDialog();
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
        new SinglePersonProvider().recommendFolk(mPerson, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                updateRecomendationButons();
                Snackbar.make(getView().recommendProfileButtton, R.string.recomended_succesfully_sring, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo_string, new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                new SinglePersonProvider().unrecommendFolk(mPerson, new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        updateRecomendationButons();
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Snackbar.make(v, R.string.couldn_recommend_tring, Snackbar.LENGTH_LONG).show();
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
        new SinglePersonProvider().unrecommendFolk(mPerson, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                updateRecomendationButons();
                Snackbar.make(getView().recommendProfileButtton, R.string.unrecommended_succesfully, Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo_string, new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                new SinglePersonProvider().recommendFolk(mPerson, new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {
                                        updateRecomendationButons();
                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {
                                        Snackbar.make(v,R.string.couldn_recommend_tring, Snackbar.LENGTH_LONG).show();
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

    public void sendMessage(final String message) {
        new PeopleService().sendMessage(message,personId, new Callback() {
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
