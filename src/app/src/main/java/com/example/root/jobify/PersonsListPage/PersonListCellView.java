package com.example.root.jobify.PersonsListPage;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.jobify.R;
import com.squareup.picasso.Picasso;

/**
 * Created by root on 07/09/16.
 */
public class PersonListCellView {

    CardView mPersonCardView;
    ImageView mPersonImageImageView;
    TextView mPersonNameTextView;
    TextView mPersonPositionTextView;

    public PersonListCellView(final View view) {
        mPersonCardView= (CardView) view;
        mPersonImageImageView = (ImageView) mPersonCardView.findViewById(R.id.person_image);
        mPersonNameTextView = (TextView) mPersonCardView.findViewById(R.id.person_name);
        mPersonPositionTextView = (TextView) mPersonCardView.findViewById(R.id.person_position);
    }

    public void setPersonName(final String name) {
        mPersonNameTextView.setText(name);
    }

    public void setPersonPosition(final String position) {
        mPersonPositionTextView.setText(position);
    }

    public void setPersonImageURL(String imageURL) {
        Picasso.with(mPersonCardView.getContext()).load(imageURL).into(mPersonImageImageView);
    }

}

