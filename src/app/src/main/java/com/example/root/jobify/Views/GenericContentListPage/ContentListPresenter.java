package com.example.root.jobify.Views.GenericContentListPage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 07/09/16.
 */
public class ContentListPresenter extends BasePresenter<ContentListFragment> {

    List<Content> mContents;
    ContentListProvider mService;
    ContentDetailPageBinder mContentDetailPageBinder;

    public ContentListPresenter(ContentListFragment viewInstance, ContentListProvider service, ContentDetailPageBinder contentDetailPageBinder) {
        super(viewInstance);
        mContents = new ArrayList<>();
        mService = service;
        mContentDetailPageBinder = contentDetailPageBinder;
    }

    public void loadContents() {
        RecyclerView ContentListView = (RecyclerView) getView().mRecyclerView;
        ContentListFragment.ContentListRecyclerViewAdapter ContentListViewAdapter = (ContentListFragment.ContentListRecyclerViewAdapter) ContentListView.getAdapter();
        ContentListViewAdapter.mValues.clear();
        for (Content Content : mContents){
            ContentListViewAdapter.mValues.add(Content);
        }
        ContentListViewAdapter.notifyDataSetChanged();
    }

    public void fetchContents() {
        mService.getContents(new Callback<ArrayList<Content>>() {
            @Override
            public void onResponse(Call<ArrayList<Content>> call, Response<ArrayList<Content>> response) {
                mContents = response.body();
                loadContents();
            }

            @Override
            public void onFailure(Call<ArrayList<Content>> call, Throwable t) {
                Log.e("GET_CONTENT",getView().getContext().getString(R.string.cant_get_content_string), t);
            }
        });

    }
}
