package com.example.root.jobify.PersonDetailPage.Experiences;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.Utilities.BasePresenter;

/**
 * Created by root on 06/09/16.
 */
public class ExperienceListCellPresenter extends BasePresenter<ExperienceListCellView> {
    Experience mExperience;

    public ExperienceListCellPresenter(ExperienceListCellView viewInstance) {
        super(viewInstance);
    }

    public void loadExperience() {
        ExperienceListCellView mView = this.getView();
        if (mExperience!=null){
            mView.setExperienceCompany(mExperience.getCompany());
            mView.setExperienceDescription(mExperience.getDescription());
            mView.setExperiencePosition(mExperience.getPosition());
            mView.setExperienceYears(mExperience.getYears());
        }
    }

    public void setExperienceParam(Experience experience) {
        mExperience = experience;
        loadExperience();
    }
}
