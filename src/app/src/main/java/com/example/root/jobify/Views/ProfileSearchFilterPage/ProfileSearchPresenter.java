package com.example.root.jobify.Views.ProfileSearchFilterPage;

import com.example.root.jobify.Services.Auth.UserAuthService;
import com.example.root.jobify.Utilities.BasePresenter;

/**
 * Created by root on 09/11/16.
 */

public class ProfileSearchPresenter extends BasePresenter<ProfileSearchFragment> {

    private UserAuthService authService;

    public ProfileSearchPresenter(ProfileSearchFragment viewInstance) {
        super(viewInstance);
        authService = UserAuthService.getInstance();
    }

    public void searchFolks() {
        ProfileSearchResultFragment profileSearchResultFragment = new ProfileSearchResultFragment();
        profileSearchResultFragment.setFilters(getView().view.getSkillInputText(),getView().view.getPositionInputText());
        getView().replaceResultList(profileSearchResultFragment);
    }

}
