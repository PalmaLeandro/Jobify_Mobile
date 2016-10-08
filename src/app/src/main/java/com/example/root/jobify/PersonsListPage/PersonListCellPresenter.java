package com.example.root.jobify.PersonsListPage;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Utilities.BasePresenter;

import java.util.Random;

/**
 * Created by root on 06/09/16.
 */
public class PersonListCellPresenter extends BasePresenter<PersonListCellView> {
    Person mPerson;

    public PersonListCellPresenter(PersonListCellView viewInstance) {
        super(viewInstance);
        //mPerson = newRandomPerson();
    }

    public void loadPerson() {
        PersonListCellView mView = this.getView();

        //populate view with data!
        if (mPerson!=null){
            mView.setPersonName(mPerson.getName());
            mView.setPersonPosition(mPerson.getPosition());
            mView.setPersonImageURL(mPerson.getImageURL());
        }
    }
    public void setPersonParam(Person person) {
        mPerson = person;
        loadPerson();
    }
}
