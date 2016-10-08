package com.example.root.jobify.PersonDetailPage;

import android.util.Log;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.Persons.SinglePersonProvider;
import com.example.root.jobify.Utilities.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 07/09/16.
 */
public class PersonDetailPresenter extends BasePresenter<PersonDetailView> {

    Person mPerson;

    public PersonDetailPresenter(PersonDetailView viewInstance) {
        super(viewInstance);
    }

    public void loadPerson() {
        PersonDetailView personDetailView = getView();
        //personDetailView.setPersonImageURL(mPerson.getImageURL());
        personDetailView.setPersonName(mPerson.getName());
        personDetailView.setPersonCity(mPerson.getCity());
        personDetailView.setPersonDob(mPerson.getDob());
        personDetailView.setPersonEmail(mPerson.getEmail());
        personDetailView.setPersonGender(mPerson.getGender());
        personDetailView.setPersonNationality(mPerson.getNationality());
        personDetailView.setPersonProfile(mPerson.getProfile());
        personDetailView.setPersonSkills(mPerson.getSkills());

    }

    public void setPersonId(String personId) {
        // TODO: active progress dialog
        new SinglePersonProvider().getPerson(personId,new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                mPerson = response.body();
                loadPerson();
                // TODO: deactive progress dialog
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e("GET_PERSON","There wasn't possible to fetch the requested person",t);
                // TODO: deactive progress dialog
            }
        });
    }
}
