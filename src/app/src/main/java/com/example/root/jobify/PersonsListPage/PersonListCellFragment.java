package com.example.root.jobify.PersonsListPage;

import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;

/**
 * Created by root on 07/09/16.
 */
public class PersonListCellFragment extends WoloxFragment<PersonListCellPresenter> {

    PersonListCellView mPersonListCellView;

    @Override
    protected int layout() {
        return R.layout.person_list_cell_fragment;
    }

    @Override
    protected void setUi(View v) {
        mPersonListCellView = new PersonListCellView(v);
    }

    @Override
    protected void init() {
        mPresenter = this.createPresenter();
    }

    @Override
    protected void populate() {
        mPresenter.loadPerson();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected PersonListCellPresenter createPresenter() {
        return new PersonListCellPresenter(this.mPersonListCellView);
    }



}
