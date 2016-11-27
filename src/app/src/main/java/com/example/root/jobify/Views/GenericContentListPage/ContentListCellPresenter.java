package com.example.root.jobify.Views.GenericContentListPage;

import com.example.root.jobify.Utilities.BasePresenter;

/**
 * Created by root on 06/09/16.
 */
public class ContentListCellPresenter extends BasePresenter<ContentListCellView> {
    private static String CONTENT_ID="contentId";

    public Content getContent() {
        return mContent;
    }

    Content mContent;

    public ContentListCellPresenter(ContentListCellView viewInstance) {
        super(viewInstance);
        //mContent = newRandomContent();
    }

    public void loadContent() {
        ContentListCellView mView = this.getView();

        //populate view with data!
        if (mContent!=null){
            mView.setContentTitle(mContent.getTitle());
            mView.setContentBase64Image(mContent.getBase64Image());
            mView.setContentSubtitle(mContent.getSubTitle());
        }
    }

    public void setContentParam(Content Content) {
        mContent = Content;
        loadContent();
    }


}