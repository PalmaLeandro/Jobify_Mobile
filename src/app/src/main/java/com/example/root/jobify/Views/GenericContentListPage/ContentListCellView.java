package com.example.root.jobify.Views.GenericContentListPage;

import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Base64InputStream;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.jobify.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by root on 07/09/16.
 */
public class ContentListCellView {

    public CardView mContentCardView;
    ImageView mContentImageImageView;
    TextView mContentTitleTextView;
    TextView mContentSubtitleTextView;

    public ContentListCellView(final View view) {
        mContentCardView= (CardView) view;
        mContentImageImageView = (ImageView) mContentCardView.findViewById(R.id.content_image);
        mContentTitleTextView = (TextView) mContentCardView.findViewById(R.id.content_title);
        mContentSubtitleTextView = (TextView) mContentCardView.findViewById(R.id.content_subtitle);
    }

    public void setContentTitle(final String contentTitle) {
        mContentTitleTextView.setText(contentTitle);
        mContentTitleTextView.setVisibility(View.VISIBLE);
    }

    public void setContentSubtitle(final String contentSubtitle) {
        if(contentSubtitle!=null){
            mContentSubtitleTextView.setText(contentSubtitle);
            mContentSubtitleTextView.setVisibility(View.VISIBLE);;
        } else {
            mContentSubtitleTextView.setVisibility(View.GONE);
            if(mContentImageImageView.getVisibility()==View.GONE)
                mContentTitleTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
    }

    public void setContentBase64Image(String base64Image) {
        if(base64Image!=null){
            try {
                mContentImageImageView.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(base64Image.getBytes(),Base64.DEFAULT))));
            }catch (Exception e){

            }
            mContentImageImageView.setVisibility(View.VISIBLE);
        } else {
            mContentImageImageView.setVisibility(View.GONE);
            mContentCardView.setRadius(0);
            mContentCardView.setElevation(0);
        }
    }

}

