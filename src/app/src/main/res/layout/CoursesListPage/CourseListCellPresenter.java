package com.example.root.ubacity.personsListPage;

import com.example.root.ubacity.Models.person;
import com.example.root.ubacity.Utilities.BasePresenter;

/**
 * Created by root on 06/09/16.
 */
public class personListCellPresenter extends BasePresenter<com.example.root.ubacity.personsListPage.personListCellView> {
    person mperson;

    public personListCellPresenter(com.example.root.ubacity.personsListPage.personListCellView viewInstance) {
        super(viewInstance);
        //mperson = newRandomperson();
    }

    public void loadperson() {
        com.example.root.ubacity.personsListPage.personListCellView mView = this.getView();

        //populate view with data!
        if (mperson!=null){
            mView.setpersonName(mperson.getName());
            mView.setpersonDescription(mperson.getDescription());
            mView.setpersonRating(mperson.getCalification());
            mView.setpersonImageURL(mperson.getImageURL());
            mView.setpersonFreshness(mperson.isNew());
            mView.setpersonOpenning(mperson.isStartingSoon() && !mperson.isNew());
        }
    }
    public void setpersonParam(person person) {
        mperson = person;
        loadperson();
    }
}
