package com.example.root.jobify.PersonDetailPage.Experiences;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.jobify.Models.Experience;
import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06/09/16.
 */
public class ExperienceListFragment extends WoloxFragment<ExperienceListPresenter> {

    RecyclerView mRecyclerView;

    @Override
    protected int layout() {
        return R.layout.experience_list_fragment;
    }

    @Override
    protected void setUi(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.experience_recicler_view);
    }

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new ExperienceListRecyclerViewAdapter(mRecyclerView.getContext()));
    }

    @Override
    protected void populate() {
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected ExperienceListPresenter createPresenter() {
        return new ExperienceListPresenter(this);
    }

    public static class ExperienceListRecyclerViewAdapter extends RecyclerView.Adapter<ExperienceListRecyclerViewAdapter.ExperienceListCellFragmentViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        public List<Experience> mValues;

        public ExperienceListRecyclerViewAdapter(Context context) {   // No se crearia con los valores sino con el presenter.
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues= new ArrayList<>();
        }

        public static class ExperienceListCellFragmentViewHolder extends RecyclerView.ViewHolder {

            public ExperienceListCellFragment experienceListCellFragment;

           public ExperienceListCellFragmentViewHolder(ExperienceListCellFragment experienceListCellFragment) {
                super(experienceListCellFragment.mExperienceListCellView.mExperienceCardView);
               this.experienceListCellFragment=experienceListCellFragment;
            }

        }

        @Override
        public ExperienceListCellFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ExperienceListCellFragment experienceListCellFragment = new ExperienceListCellFragment();
            experienceListCellFragment.onCreateView(LayoutInflater.from(parent.getContext()),parent,null);
            return new ExperienceListCellFragmentViewHolder(experienceListCellFragment);
        }

        @Override
        public void onBindViewHolder(ExperienceListCellFragmentViewHolder holder, int position) {
            Experience experienceToPresent = mValues.get(position);
            holder.experienceListCellFragment.mPresenter.setExperienceParam(experienceToPresent);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

}
