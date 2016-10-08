package com.example.root.jobify.PersonDetailPage.Experiences;

import android.view.View;

import com.example.root.jobify.R;
import com.example.root.jobify.Utilities.WoloxFragment;

/**
 * Created by root on 07/09/16.
 */
public class ExperienceListCellFragment extends WoloxFragment<com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListCellPresenter> {

    com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListCellView mExperienceListCellView;

    @Override
    protected int layout() {
        return R.layout.experience_list_cell_fragment;
    }

    @Override
    protected void setUi(View v) {
        mExperienceListCellView = new com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListCellView(v);
    }

    @Override
    protected void init() {
        mPresenter = this.createPresenter();
    }

    @Override
    protected void populate() {
        mPresenter.loadExperience();
    }

    @Override
    protected void setListeners() {
        final ExperienceListCellFragment self = this;
        mExperienceListCellView.mExperienceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.experienceClicked();
            }
        });
    }

    @Override
    protected com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListCellPresenter createPresenter() {
        return new com.example.root.jobify.PersonDetailPage.Experiences.ExperienceListCellPresenter(this.mExperienceListCellView);
    }

    public void experienceClicked() {
        /*
        Context context = mExperienceView.getContext();
        Intent intent = new Intent(context, ExperienceDetailActivity.class);
        intent.putExtra(ExperienceDetailActivity.EXTRA_NAME, holder.mBoundString);
        // Esto no se bien como se hace, pero crea la actividad ExperienceDetail y le asigna el curso a mostrar y la empieza.
        context.startActivity(intent);
        */
    }


}
