package com.example.root.jobify.Views.GenericContentListPage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxActivity;
import com.example.root.jobify.Utilities.WoloxFragment;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.CONTENT_ID;

/**
 * Created by root on 06/09/16.
 */

public abstract class ContentListFragment extends WoloxFragment<ContentListPresenter> implements ContentDetailPageBinder{

    protected RecyclerView mRecyclerView;
    public static String CONTENT_ID = "contentId";
    public static String CONTENT_NAME = "contentName";

    @Override
    protected int layout() {
        return R.layout.content_list_fragment;
    }

    @Override
    protected void setUi(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.content_recicler_view);
    }

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new ContentListRecyclerViewAdapter(mRecyclerView.getContext(),this));
    }

    @Override
    protected void populate() {
        mPresenter.fetchPeople();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected ContentListPresenter createPresenter() {
        return new ContentListPresenter(this, createService(),this);
    }

    @Override
    public void onResume() {
        super.onResume();
        populate();
    }


    protected abstract ContentListProvider createService();

    public Class<? extends WoloxActivity> getContentDetailActivity(){return null;};

    public static class ContentListRecyclerViewAdapter extends RecyclerView.Adapter<ContentListRecyclerViewAdapter.ContentListCellFragmentViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        public List<Content> mValues;
        private ContentDetailPageBinder contentDetailPageBinder;

        public ContentListRecyclerViewAdapter(Context context,ContentDetailPageBinder contentDetailPageBinder) {
            this.contentDetailPageBinder = contentDetailPageBinder;   // No se crearia con los valores sino con el presenter.
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues= new ArrayList<>();
        }

        public static class ContentListCellFragmentViewHolder extends RecyclerView.ViewHolder {

            public ContentListCellFragment ContentListCellFragment;

           public ContentListCellFragmentViewHolder(ContentListCellFragment ContentListCellFragment) {
                super(ContentListCellFragment.mContentListCellView.mContentCardView);
               this.ContentListCellFragment=ContentListCellFragment;
            }

        }

        @Override
        public ContentListCellFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ContentListCellFragment ContentListCellFragment = new ContentListCellFragment();
            ContentListCellFragment.onCreateView(LayoutInflater.from(parent.getContext()),parent,null);
            return new ContentListCellFragmentViewHolder(ContentListCellFragment);
        }

        @Override
        public void onBindViewHolder(final ContentListCellFragmentViewHolder holder, final int position) {
            holder.ContentListCellFragment.mPresenter.setContentParam(mValues.get(position));
            contentDetailPageBinder.setListener(mValues.get(position),holder.ContentListCellFragment.mContentListCellView.mContentCardView);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    @Override
    public void setListener(final Content content, View contentView) {
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, getContentDetailActivity());
                intent.putExtra(CONTENT_ID, content.getId());
                context.startActivity(intent);
            }
        });
    }
}
