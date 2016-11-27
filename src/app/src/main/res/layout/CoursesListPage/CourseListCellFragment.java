package com.example.root.ubacity.personsListPage;

import android.view.View;

import com.example.root.ubacity.R;
import com.example.root.ubacity.Utilities.WoloxFragment;

/**
 * Created by root on 07/09/16.
 */
public class personListCellFragment extends WoloxFragment<personListCellPresenter> {

    com.example.root.ubacity.personsListPage.personListCellView mpersonListCellView;

    @Override
    protected int layout() {
        return R.layout.person_list_cell_fragment;
    }

    @Override
    protected void setUi(View v) {
        mpersonListCellView = new com.example.root.ubacity.personsListPage.personListCellView(v);
    }

    @Override
    protected void init() {
        mPresenter = this.createPresenter();
    }

    @Override
    protected void populate() {
        mPresenter.loadperson();
    }

    @Override
    protected void setListeners() {
        final com.example.root.ubacity.personsListPage.personListCellFragment self = this;
        mpersonListCellView.mpersonCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.personClicked();
            }
        });
    }

    @Override
    protected personListCellPresenter createPresenter() {
        return new personListCellPresenter(this.mpersonListCellView);
    }

    public void personClicked() {
        /*
        Context context = mpersonView.getContext();
        Intent intent = new Intent(context, personDetailActivity.class);
        intent.putExtra(personDetailActivity.EXTRA_NAME, holder.mBoundString);
        // Esto no se bien como se hace, pero crea la actividad personDetail y le asigna el curso a mostrar y la empieza.
        context.startActivity(intent);
        */
    }


}
