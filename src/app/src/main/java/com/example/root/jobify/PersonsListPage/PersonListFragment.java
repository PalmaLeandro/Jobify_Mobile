package com.example.root.jobify.PersonsListPage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.jobify.PersonDetailPage.PersonDetailActivity;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06/09/16.
 */
public class PersonListFragment extends WoloxFragment<PersonListPresenter> {
    private String categoryId;
    private String subCategoryId;
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
        mRecyclerView.setAdapter(new PersonListRecyclerViewAdapter(mRecyclerView.getContext()));
    }

    @Override
    protected void populate() {
        //mPresenter.setPersonsParam("","");
        //mPresenter.setPersonsParam(getArguments().getString("person_list_provider_id"));
        //mPresenter.loadPersons();
    }

    @Override
    protected void setListeners() {

    }


    @Override
    protected PersonListPresenter createPresenter() {
        return new PersonListPresenter(this);
    }

    public void setUsername(String username) {
        createPresenter().setPersonsParam(username);
    }

    public static class PersonListRecyclerViewAdapter extends RecyclerView.Adapter<PersonListRecyclerViewAdapter.PersonListCellFragmentViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        public List<Person> mValues;

        public PersonListRecyclerViewAdapter(Context context) {   // No se crearia con los valores sino con el presenter.
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues= new ArrayList<>();
        }

        public static class PersonListCellFragmentViewHolder extends RecyclerView.ViewHolder {

            public PersonListCellFragment personListCellFragment;

           public PersonListCellFragmentViewHolder(PersonListCellFragment personListCellFragment) {
                super(personListCellFragment.mPersonListCellView.mPersonCardView);
               this.personListCellFragment=personListCellFragment;
            }

        }

        @Override
        public PersonListCellFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            PersonListCellFragment personListCellFragment = new PersonListCellFragment();
            personListCellFragment.onCreateView(LayoutInflater.from(parent.getContext()),parent,null);
            return new PersonListCellFragmentViewHolder(personListCellFragment);
        }

        @Override
        public void onBindViewHolder(final PersonListCellFragmentViewHolder holder, final int position) {
            holder.personListCellFragment.mPresenter.setPersonParam(mValues.get(position));

            holder.personListCellFragment.mPersonListCellView.mPersonCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PersonDetailActivity.class);
                    intent.putExtra(PersonDetailActivity.PERSON_USERNAME, mValues.get(position).getUsername());

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
