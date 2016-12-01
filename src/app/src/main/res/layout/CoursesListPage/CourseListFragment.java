package com.example.root.ubacity.personsListPage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.ubacity.personDetailPage.personDetailActivity;
import com.example.root.ubacity.Models.person;
import com.example.root.ubacity.Models.Providers.personsProvider;
import com.example.root.ubacity.R;
import com.example.root.ubacity.Utilities.WoloxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06/09/16.
 */

public abstract class personListFragment extends WoloxFragment<personListPresenter> {

    RecyclerView mRecyclerView;

    @Override
    protected int layout() {
        return R.layout.person_list_fragment;
    }

    @Override
    protected void setUi(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.person_recicler_view);
    }

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new personListRecyclerViewAdapter(mRecyclerView.getContext()));
    }

    @Override
    protected void populate() {
        mPresenter.fetchpersons();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected personListPresenter createPresenter() {
        return new personListPresenter(this, createService());
    }

    protected abstract personsProvider createService();

    public static class personListRecyclerViewAdapter extends RecyclerView.Adapter<personListRecyclerViewAdapter.personListCellFragmentViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        public List<person> mValues;

        public personListRecyclerViewAdapter(Context context) {   // No se crearia con los valores sino con el presenter.
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues= new ArrayList<>();
        }

        public static class personListCellFragmentViewHolder extends RecyclerView.ViewHolder {

            public personListCellFragment personListCellFragment;

           public personListCellFragmentViewHolder(personListCellFragment personListCellFragment) {
                super(personListCellFragment.mpersonListCellView.mpersonCardView);
               this.personListCellFragment=personListCellFragment;
            }

        }

        @Override
        public personListCellFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            personListCellFragment personListCellFragment = new personListCellFragment();
            personListCellFragment.onCreateView(LayoutInflater.from(parent.getContext()),parent,null);
            return new personListCellFragmentViewHolder(personListCellFragment);
        }

        @Override
        public void onBindViewHolder(final personListCellFragmentViewHolder holder, final int position) {
            holder.personListCellFragment.mPresenter.setpersonParam(mValues.get(position));

            holder.personListCellFragment.mpersonListCellView.mpersonCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, personDetailActivity.class);
                    intent.putExtra(personDetailActivity.person_ID_KEY, mValues.get(position).getId());

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }


    }

}
