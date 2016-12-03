package com.example.root.jobify.Views.ProfileSearchFilterPage;

import android.content.Context;
import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;
import com.example.root.jobify.Views.GenericContentListPage.ContentListFragment;

/**
 * Created by root on 09/11/16.
 */
public class ProfileSearchFragment extends WoloxFragment<ProfileSearchPresenter>  {

    Context mContext;
    ProfileSearchView view;

    @Override
    protected int layout() {
        return R.layout.profile_search_page;
    }

    @Override
    protected void setUi(View v) {
        mContext = v.getContext();
        view=new ProfileSearchView(v);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void populate() {
        mPresenter=createPresenter();
        mPresenter.loadSkills();
        mPresenter.loadJobPositions();
    }

    @Override
    protected void setListeners() {
        view.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.searchFolks();
            }
        });
    }

    @Override
    protected ProfileSearchPresenter createPresenter() {
        return new ProfileSearchPresenter(this);
    }


    ContentListFragment searchResultListFragment;
    void replaceResultList(ContentListFragment contentListFragment){
        view.showResultsTitle();
        if(searchResultListFragment!=null)
            searchResultListFragment.getView().setVisibility(View.GONE);
        searchResultListFragment=contentListFragment;
        replaceFragment(R.id.folks_list,contentListFragment);
    }

}
