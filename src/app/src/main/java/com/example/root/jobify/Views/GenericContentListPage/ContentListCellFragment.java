package com.example.root.jobify.Views.GenericContentListPage;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;

/**
 * Created by root on 07/09/16.
 */
public class ContentListCellFragment extends WoloxFragment<ContentListCellPresenter> {

    ContentListCellView mContentListCellView;

    @Override
    protected int layout() {
        return R.layout.content_list_cell_fragment;
    }

    @Override
    protected void setUi(View v) {
        mContentListCellView = new ContentListCellView(v);
    }

    @Override
    protected void init() {
        mPresenter = this.createPresenter();
    }

    @Override
    protected void populate() {
        mPresenter.loadContent();
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected ContentListCellPresenter createPresenter() {
        return new ContentListCellPresenter(this.mContentListCellView);
    }

}
